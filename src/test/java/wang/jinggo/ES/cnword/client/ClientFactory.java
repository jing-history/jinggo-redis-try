package wang.jinggo.ES.cnword.client;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ClientFactory {

	public static Client create(){
		Settings settings = Settings.builder()
				.put("client.transport.sniff", true).build();
		TransportClient client = new PreBuiltTransportClient(settings);

		return client;
	}
}
