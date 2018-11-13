package wang.jinggo.ES.cnword.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

public class PagingTest {
	public static void main(String[] args) {
		int rows=10; //一页显示多少条搜索结果
		int offset=0;  //开始行
		
		// 获取客户端
		Client client = null;//QueryTest.getClient();

		String index = "news";//"test1"; // 索引名

		// 创建查询索引,参数productindex表示要查询的索引库为productindex
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);

		// 设置查询索引类型,setTypes("productType1", "productType2","productType3");
		// 用来设定在多个类型中搜索
		// searchRequestBuilder.setTypes(index);

		// 设置查询类型 1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询 2.SearchType.SCAN =
		// 扫描查询,无序
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);

		String keyWords = "软件";//"工作"; //"大全"; // 查询词
		// 设置查询关键词
		QueryStringQueryBuilder qb = new QueryStringQueryBuilder(keyWords);
		searchRequestBuilder.setQuery(qb);

		// 分页应用
		searchRequestBuilder.setFrom(offset).setSize(rows);
		
		// 执行搜索,返回搜索响应信息
		SearchResponse response = searchRequestBuilder.execute().actionGet();

		// 获取搜索的文档结果
		SearchHits searchHits = response.getHits();
		long totalHits = searchHits.getTotalHits();  //得到结果总数
		System.out.println("totalHits:"+totalHits);
		// ObjectMapper mapper = new ObjectMapper();
		int count =0;
		for (SearchHit hit : searchHits) {
			//System.out.println("hit " + hit);
			// 将文档中的每一个对象转换json串值
			String json = hit.getSourceAsString(); //搜索结果用Gson解析。  解析都要自己写的
			System.out.println((++count)+" : " + json);
		}
	}

}
