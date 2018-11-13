package wang.jinggo.ES.cnword.index;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;

public class TestSetting {

	public static void main(String[] args) throws IOException {

		Client client=null;
		IndicesAdminClient ac = client.admin().indices();
		String index="test1";
		CreateIndexRequestBuilder builder = ac.prepareCreate(index);
		
		Builder setting = Settings.builder().put("number_of_shards", 1);		
		builder.setSettings(setting);

	}

}
