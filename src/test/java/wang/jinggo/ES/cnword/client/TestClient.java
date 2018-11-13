package wang.jinggo.ES.cnword.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import junit.framework.TestCase;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class TestClient extends TestCase {

	public static void main(String[] args) throws UnknownHostException {
		Settings settings = Settings.builder()
				.put("cluster.name", "ElasticSearchClusterName").build();

		TransportClient client = new PreBuiltTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress
						.getByName("192.168.10.150"), 9200));
	}

	public static void testMutli() throws UnknownHostException {
		Client client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("host1"), 9300))
				.addTransportAddress(
						new InetSocketTransportAddress(InetAddress
								.getByName("host2"), 9300));
		// 使用连接…
		client.close();
	}
	
	public static void testLocal()throws UnknownHostException{
		String HOST_NAME = "60.205.165.29";
		int PORT = 9300;
		
		Client client = new PreBuiltTransportClient(Settings.EMPTY)
		.addTransportAddress(
				new InetSocketTransportAddress(InetAddress
						.getByName(HOST_NAME), PORT));
	}

	public static void test2() {
		Settings settings = Settings.builder()
				.put("cluster.name", "ElasticSearchClusterName").build();
		Client client = new PreBuiltTransportClient(settings);
	}

	public static void test3() {
		Settings settings = Settings.builder()
				.put("client.transport.sniff", true).build();
		TransportClient client = new PreBuiltTransportClient(settings);

	}

}
