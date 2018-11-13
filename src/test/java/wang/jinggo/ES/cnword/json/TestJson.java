package wang.jinggo.ES.cnword.json;

import java.io.IOException;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

public class TestJson {

	public static void main(String[] args) throws IOException {
		XContentBuilder b = XContentFactory.jsonBuilder().startObject();
		b.field("title", "新闻标题");
		b.field("body", "内容");
		b.endObject();

		// from XContent to JSON
		String json = b.bytes().utf8ToString();
		System.out.println(json);
	}

}
