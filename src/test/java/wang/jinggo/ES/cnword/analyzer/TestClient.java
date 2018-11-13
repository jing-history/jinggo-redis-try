package wang.jinggo.ES.cnword.analyzer;

import org.elasticsearch.index.query.QueryBuilders;

public class TestClient {

	public static void main(String[] args) {
		//Client client = QueryTest.getClient();
		System.out.println(QueryBuilders.queryStringQuery("我爱北京天安门").analyzer("cn"));
	}

}
