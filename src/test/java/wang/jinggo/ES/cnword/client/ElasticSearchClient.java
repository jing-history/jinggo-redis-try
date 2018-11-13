package wang.jinggo.ES.cnword.client;

import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.google.gson.Gson;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * Created by gpzpati on 10/16/13.
 */
public class ElasticSearchClient {

	private static final String HOST_NAME = "localhost";
	private static final int PORT = 9300;

	private static Client client;

	private Client getClient() {
		if (client == null) {
			// Builder settings = Settings.builder().put("cluster.name",
			// "ElasticSearchClusterName");
			Settings settings = Settings.builder()
					.put("cluster.name", "ElasticSearchClusterName").build();

			try {
				client = new PreBuiltTransportClient(settings)
						.addTransportAddress(new InetSocketTransportAddress(
								InetAddress.getByName("192.168.10.150"), 9200));
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}

			// TransportClient client = new PreBuiltTransportClient(settings)
			// .addTransportAddress(new
			// InetSocketTransportAddress(InetAddress.getByName("host1"), 9300))
			// .addTransportAddress(new
			// InetSocketTransportAddress(InetAddress.getByName("host2"),
			// 9300));

			// Settings settings = ImmutableSettings.settingsBuilder()
			// .put("cluster.name", "mysearch").build();

			// client = new TransportClient(settings)
			// .addTransportAddress(new InetSocketTransportAddress(
			// HOST_NAME, PORT));

		}
		return client;
	}

	public Map getRecordById(String index, String type, String id) {
		Client client = getClient();
		GetResponse getResponse = client.prepareGet(index, type, id).execute()
				.actionGet();
		return getResponse.getSource();
	}

	public BulkResponse bulkInsert(String index, String type, Map sourceMap) {
		Client client = getClient();
		BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
		bulkRequestBuilder.add(client.prepareIndex(index, type).setSource(
				sourceMap));
		BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
		return bulkResponse;
	}

	/**
	 * 初始化所有索引
	 * 
	 * @param indexName
	 * @param typeName
	 * @throws Exception
	 */
	public int initAllIndex(String indexName, String typeName) throws Exception {
		// 获取客户端
		/*
		 * Client client = indexHelper.getClient(); int count = 0; //创建批量请求
		 * BulkRequestBuilder bulkRequest = client.prepareBulk(); //获取所有产品列表
		 * List<Map<String, Object>> list = getAllProduct(); for (Map<String,
		 * Object> product : list) { //循环产品列表，逐个生成Source，加入批量请求
		 * bulkRequest.add(client.prepareIndex(indexName, typeName)
		 * .setSource(getDoc(product))); count++; } //发送批量请求，获取批量回调 BulkResponse
		 * bulkResponse = bulkRequest.execute().actionGet(); if
		 * (bulkResponse.hasFailures()) { //如果批量回调返回失败，写日志，打印错误 //TODO写日志
		 * System.err.println(bulkResponse.buildFailureMessage()); count = -1; }
		 * return count;
		 */
		return 0;
	}

	public IndexResponse insert(String index, String type,
			Map<String, String> data) {
		Gson gson = new Gson();
		String json = gson.toJson(data); // 把Map转换成json字符串
		Client client = getClient();
		IndexRequestBuilder indexBuilder = client.prepareIndex(index, type)
				.setSource(json);
		IndexResponse response = indexBuilder.execute().actionGet();
		return response;
	}

	public void insert(String index, String type, String json) {
		Client client = getClient();
		IndexRequestBuilder indexBuilder = client.prepareIndex(index, type)
				.setSource(json);
		IndexResponse response = indexBuilder.execute().actionGet();
	}

	public void deleteRecord(String index, String type, String id) {
		Client client = getClient();
		DeleteResponse deleteResponse = client.prepareDelete(index, type, id)
				.execute().actionGet();
		System.out.println(deleteResponse.getId());
	}

	/**
	 * 删除索引库，先判断索引是否存在，存在才进行删除
	 * 
	 * @param indexName
	 * @return
	 */
	public boolean deleteIndex(String indexName) {
		try {
			Client client = getClient();
			IndicesExistsResponse indicesExistsResponse = client.admin()
					.indices().prepareExists(indexName).execute().actionGet();
			if (indicesExistsResponse.isExists()) {
				// System.out.println("Exists");
				IndicesAdminClient admin = client.admin().indices();
				return admin.prepareDelete(indexName).execute().actionGet()
						.isAcknowledged();
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static String getIndexName() {
		// TODO Auto-generated method stub
		return null;
	}
}