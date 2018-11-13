package wang.jinggo.ES.cnword.client;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentFactory;

public class TestXContentFactory {

	public static void main(String[] args) throws IOException {
		String jsonStr = XContentFactory.jsonBuilder().startObject()
		.field("number_of_shards", 1).endObject().string();
		System.out.println(jsonStr);
	}

}
