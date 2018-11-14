package wang.jinggo.ES.cnword.search;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.pipeline.bucketscript.BucketScriptPipelineAggregationBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import wang.jinggo.ES.cnword.client.ClientBuilder;


public class TestAggregationBuilders {

	public static void main(String[] args) throws UnknownHostException {
		Settings settings = Settings.builder()
				.put("cluster.name", "ElasticSearchClusterName").build();

		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress
						.getByName("192.168.10.150"), 9200));

		String index = "test1"; // 索引名
		TermQueryBuilder qb = QueryBuilders.termQuery("price", 20);

		Map<String, String> bucketsPathsMap = new HashMap<>();
		bucketsPathsMap.put("count", "_count");
		Script script = new Script("count / 1000");

		BucketScriptPipelineAggregationBuilder bs = PipelineAggregatorBuilders
				.bucketScript("agg_values", bucketsPathsMap, script);

		TermsAggregationBuilder oid = AggregationBuilders.terms("agg_oid")
				.field("oid").subAggregation(bs);

		SearchResponse sr = client.prepareSearch(index).setQuery(qb)
				.addAggregation(oid).execute().actionGet();
	}

	public static void testBasic() {
		Client client = ClientBuilder.getSingleton();

		String index = "test1"; // 索引名
		TermQueryBuilder qb = QueryBuilders.termQuery("price", 20);

		TermsAggregationBuilder ab = AggregationBuilders.terms("version")
				.field("version"); // 分类列

		ab.size(100);  //只显示前100个统计结果
		
		SearchResponse sr = client.prepareSearch(index).setQuery(qb)
				.addAggregation(ab) // // 增加分组查询到搜索请求
				.execute().actionGet();
		
		
		

	}

}
