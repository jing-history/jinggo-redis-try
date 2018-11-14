package wang.jinggo.ES.cnword.search;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import wang.jinggo.ES.cnword.client.ElasticSearchClient;

public class TestSroll {

	public static void main(String[] args) {
		Client esClient = null;
		SearchResponse searchResponse = esClient
				.prepareSearch(ElasticSearchClient.getIndexName()).setSize(5)
				// 这个游标维持多长时间
				.setScroll(TimeValue.timeValueMinutes(8)).execute().actionGet();
		System.out.println(searchResponse.getHits().getTotalHits());
		searchResponse = esClient
				.prepareSearchScroll(searchResponse.getScrollId())
				.setScroll(TimeValue.timeValueMinutes(8)).execute().actionGet();
		System.out.println(searchResponse.getHits().getTotalHits());
		System.out.println(searchResponse.getHits().hits().length);
		for (SearchHit hit : searchResponse.getHits()) {
			System.out.println(hit.getSourceAsString());
		}

	}

	public static void testDump() throws UnknownHostException,
			InterruptedException {
		String sourceIp = "";
		// new InetSocketTransportAddress(InetAddress
		// .getByName(sourceIp), 9200);

		String clustName = "";
		Settings settings = Settings.builder().put("cluster.name", clustName)
				.put("client.transport.sniff", true)
				.put("client.transport.ping_timeout", "30s")
				.put("client.transport.nodes_sampler_interval", "30s").build();
		TransportClient client = new PreBuiltTransportClient(settings);
		client.addTransportAddress(new InetSocketTransportAddress(InetAddress
				.getByName(sourceIp), 9200));

		String destiClustName = "";
		// build destination settings
		Settings destiSettings = Settings.builder()
				.put("cluster.name", destiClustName)
				.put("client.transport.sniff", true)
				.put("client.transport.ping_timeout", "30s")
				.put("client.transport.nodes_sampler_interval", "30s").build();
		TransportClient destiClient = new PreBuiltTransportClient(destiSettings);
		String destiIp = "";
		destiClient.addTransportAddress(new InetSocketTransportAddress(
				InetAddress.getByName(destiIp), 9200));

		String indexName = "";
		String destiIndexName = "";
		SearchResponse scrollResp = client.prepareSearch(indexName)
				.setScroll(new TimeValue(60000)).setSize(1000).execute()
				.actionGet();
		String destiIndexType = "";
		// build destination bulk
		BulkRequestBuilder bulk = destiClient.prepareBulk();

		ExecutorService executor = Executors.newFixedThreadPool(5);
		while (true) {
			bulk = destiClient.prepareBulk();
			final BulkRequestBuilder bulk_new = bulk;
			for (SearchHit hit : scrollResp.getHits().getHits()) {
				IndexRequest req = destiClient.prepareIndex()
						.setIndex(destiIndexName).setType(destiIndexType)
						.setSource(hit.getSourceAsString()).request();
				bulk_new.add(req);
			}
			executor.execute(new Runnable() {
				@Override
				public void run() {
					bulk_new.execute();
				}
			});

			Thread.sleep(10);

			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId())
					.setScroll(new TimeValue(60000)).execute().actionGet();
			if (scrollResp.getHits().getHits().length == 0) {
				break;
			}
		}
	}

}
