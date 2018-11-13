package wang.jinggo.ES.cnword.search;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QueryTest {
	public static void main(String[] args) {
		// termSearch2();
		// termSearch();
		// matchAll();
		// cnMatch();
		// enMatch();
		//fieldQyTest();
		//fieldQyBoostTest();
		// testGson();
		//testPhraseQuery();
		testBooleanQuery();
	}

	public static void testBooleanQuery(){
		String keyWords = "在听取了区种植中心主任方玉祥就全区农机化工作情况汇报后";//"工作顺利完成"; //
		String field = "body"; //"title"
		MatchPhraseQueryBuilder pqBody = QueryBuilders.matchPhraseQuery(field, keyWords);
		
		field = "title";
		MatchPhraseQueryBuilder pqTitle = QueryBuilders.matchPhraseQuery(field, keyWords);
		
		QueryStringQueryBuilder fuzzyQb = new QueryStringQueryBuilder(keyWords);
		
		QueryBuilder qb = QueryBuilders.boolQuery().should(pqBody).should(pqTitle).should(fuzzyQb);
		
		String index = "news"; // 索引名

		Client client = null;//getClient();
		SearchResponse searchResponse = client.prepareSearch(index)
				.setQuery(qb).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		
		long totalHits = hits.getTotalHits();  //得到结果总数
		System.out.println("totalHits:"+totalHits);

		for (SearchHit hit : hits) {
			Map<String, Object> result = hit.getSource(); // 键是列名，值是文档中该列的值
			for (final Entry<String, Object> entry : result.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println("score: " + hit.getScore());
		}
		// on shutdown
		client.close();
	}
	
	public static void testBooleanPhraseQuery(){
		String keyWords = "工作顺利完成"; //"自2006年8月2日北京市";//
		String field = "body"; //"title"
		MatchPhraseQueryBuilder pqBody = QueryBuilders.matchPhraseQuery(field, keyWords);
		
		field = "title";
		MatchPhraseQueryBuilder pqTitle = QueryBuilders.matchPhraseQuery(field, keyWords);
		
		QueryBuilder qb = QueryBuilders.boolQuery().should(pqBody).should(pqTitle);
		
		String index = "news";
		// "test1"; // 索引名

		Client client = null;//getClient();
		SearchResponse searchResponse = client.prepareSearch(index)
				.setQuery(qb).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		
		long totalHits = hits.getTotalHits();  //得到结果总数
		System.out.println("totalHits:"+totalHits);
		Gson gson = new Gson();

		for (SearchHit hit : hits) {
			String j = hit.getSourceAsString();
			// System.out.println(j);
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> retMap2 = gson.fromJson(j, type);
			for (final Entry<String, String> entry : retMap2.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println("score: " + hit.getScore());
			// System.out.println(hit.);
		}
		// on shutdown
		client.close();
	}
	public static void testPhraseQuery(){
		String keyWords = "自2006年8月2日北京市";//"工作顺利完成"; //"工作顺利完成"; //
		String field = "body"; //"title"
		MatchPhraseQueryBuilder qb = QueryBuilders.matchPhraseQuery(field, keyWords);

		String index = "news";
		// "test1"; // 索引名

		Client client = null;//getClient();
		SearchResponse searchResponse = client.prepareSearch(index)
				.setQuery(qb).execute().actionGet();
		SearchHits hits = searchResponse.getHits();

		long totalHits = hits.getTotalHits();  //得到结果总数
		System.out.println("totalHits:"+totalHits);
		Gson gson = new Gson();

		for (SearchHit hit : hits) {
			String j = hit.getSourceAsString();
			// System.out.println(j);
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> retMap2 = gson.fromJson(j, type);
			for (final Entry<String, String> entry : retMap2.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println("score: " + hit.getScore());
			// System.out.println(hit.);
		}
		// on shutdown
		client.close();
	}

	// 中文分词
	public static void cnMatch() {
		String keyWords = "自2006年8月2日北京市";// "中文";// "汉语词法或其它汉语语言知识进行分词";
		QueryStringQueryBuilder qb = new QueryStringQueryBuilder(keyWords);
		// qb.analyzer("cn").field("title");

		String index = "news";
		// "test1"; // 索引名

		Client client = null;//getClient();
		SearchResponse searchResponse = client.prepareSearch(index)
				.setQuery(qb).execute().actionGet();
		SearchHits hits = searchResponse.getHits();

		Gson gson = new Gson();

		for (SearchHit hit : hits) {
			String j = hit.getSourceAsString();
			// System.out.println(j);
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> retMap2 = gson.fromJson(j, type);
			for (final Entry<String, String> entry : retMap2.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println("score: " + hit.getScore());
			// System.out.println(hit.);
		}
		// on shutdown
		client.close();
	}

	public static void testMatchQuery() {
		String keyWords = "中文";
		// (name contains "foo" or name contains "bar") AND ( date equals today)
		// 的写法： matchQuery("name", "foo bar")
		// rangeQuery("date").from("2013-02-05").to("2013-02-06")
		MatchQueryBuilder queryBuilder = QueryBuilders
				.matchQuery("title", keyWords).minimumShouldMatch("2")
				.operator(MatchQueryBuilder.DEFAULT_OPERATOR);
	}

	public static void testGson() {
		Gson gson = new Gson();

		String j = "{\"body\":\"近日，国家质量监督检验检疫总局和国家标准化管理委员会发布了新修订的《饲料标签》（GB10648-19990将于2014年7月1日起正式实施。 1999版国家标准《饲料标签》已运行多年，在一些方面急需改进。此次发布的2013版新标准进一步完善了标准的适用范围，增加了术语的定义；采用通用名称的要求；饲料添加剂、微量元素预混合饲料和微生物预混合饲料应标明推荐用量及注意事项的规定；产品的特殊标示规定：规定进口产品的中文标签标明的生产日期应与原产地标签上标明的生产日期一致；规定了标签不得被遮掩，应在不打开包装的情况下，能看到完整的标签内容等。\",\"title\":\"《饲料标签》新标准正式发布\"}";
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> retMap2 = gson.fromJson(j, type);
		for (final Entry<String, String> entry : retMap2.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}

	public static void enMatch() {
		String keyWords = "大全";// "汉语词法或其它汉语语言知识进行分词";
		QueryStringQueryBuilder qb = new QueryStringQueryBuilder(keyWords);
		// qb.analyzer("cn").field("title");

		String index = "test1"; // 索引名

		Client client = null;//getClient();
		SearchResponse searchResponse = client.prepareSearch(index)
				.setQuery(qb).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		for (SearchHit hit : hits) {
			System.out.println(hit.getSourceAsString());
			System.out.println("score: " + hit.getScore());
			// System.out.println(hit.);
		}
		// on shutdown
		client.close();
	}

	public static void matchAll() {
		QueryBuilder query = QueryBuilders.matchAllQuery();
		String index = "news";
		// "test1"; // 索引名

		Client client = null;//getClient();
		SearchResponse searchResponse = client.prepareSearch(index)
				.setQuery(query).execute().actionGet();
		SearchHits hits = searchResponse.getHits();
		for (SearchHit hit : hits) {
			System.out.println(hit.getSourceAsString());
		}
		// on shutdown
		client.close();
	}


}
