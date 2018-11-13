package wang.jinggo.ES.cnword.search;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.SearchHit;

import client.ElasticSearchClient;

public class ScrollTest {

	public static void main(String[] args) {
		Client esClient = null;// ESUtils.getClient();
		SearchResponse searchResponse = esClient
				.prepareSearch(ElasticSearchClient.getIndexName())
				// 加上这个据说可以提高性能，但第一次却不返回结果
				//.setSearchType(SearchType.QUERY_THEN_FETCH)
				// 实际返回的数量为5*index的主分片格式
				.setSize(5)
				// 这个游标维持多长时间
				.setScroll(TimeValue.timeValueMinutes(8)).execute().actionGet();
		// 第一次查询，只返回数量和一个scrollId
		System.out.println(searchResponse.getHits().getTotalHits());
		System.out.println(searchResponse.getHits().hits().length);
		// 第一次运行没有结果
		for (SearchHit hit : searchResponse.getHits()) {
			System.out.println(hit.getSourceAsString());
		}
		System.out.println("------------------------------");
		// 使用上次的scrollId继续访问
		searchResponse = esClient
				.prepareSearchScroll(searchResponse.getScrollId())
				.setScroll(TimeValue.timeValueMinutes(8)).execute().actionGet();
		System.out.println(searchResponse.getHits().getTotalHits());
		System.out.println(searchResponse.getHits().hits().length);
		for (SearchHit hit : searchResponse.getHits()) {
			System.out.println(hit.getSourceAsString());
		}
	}
}
