package wang.jinggo;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.util.ElasticsearchUtils;


import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 控对redis的更新和删除
 * @author wangyj
 * @description
 * @create 2018-08-20 17:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    private static final Logger logger = LoggerFactory.getLogger(JinggoRedisTryApplicationTests.class);

    @Autowired
    ElasticsearchUtils elasticsearchUtils;

    @Autowired
    TransportClient transportClient;

    @Test
    public void createESindex(){
        CreateIndexRequestBuilder createIndexRequestBuilder = transportClient.admin()
                .indices().prepareCreate("test1");  //创建称为test1 的索引
        CreateIndexResponse response = createIndexRequestBuilder.execute().actionGet();
        logger.info(String.valueOf(response.isAcknowledged()));
    }

    //在mapping 中定义字段类型
    public void setESmapping() throws IOException {
        Settings settings = Settings.builder().
                loadFromSource(
                jsonBuilder()
                        .startObject()
                        .startObject("analysis")
                        .startObject("analyzer")
                        .startObject("synonym")
                        .field("tolenizer","whitespace")
                        .field("filter",new String[]{"synonym"})
                        .endObject()
                        .endObject()
                        .startObject("filter")
                        .startObject("synonym")
                        .field("type","synonym")
                        .field("synonym", new String[]{"Highlandstreet,ravioli"})
                        .field("ignore_case", true)
                        .field("expand",true)
                        .endObject()
                        .endObject()
                        .endObject()
                        .endObject().string(), XContentType.JSON).build();
        XContentBuilder content = jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("title")
                .field("type","String")
                .field("analyzer","synonym")
                .endObject()
                .startObject("location")
                .field("type","geo_point")
                .endObject()
                .endObject()
                .endObject();


        CreateIndexResponse response = transportClient.admin()
                .indices().prepareCreate("index_name")
                .setSettings(settings).addMapping("mapping_name1",content).get();
    }
}
