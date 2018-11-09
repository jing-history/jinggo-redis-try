package wang.jinggo.ES;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.JinggoRedisTryApplicationTests;

import java.io.IOException;

/**
 * ES 搜索引擎开发实战
 * @author wangyj
 * @description
 * @create 2018-11-09 20:15
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActualCombatBook {

    private static final Logger logger = LoggerFactory.getLogger(JinggoRedisTryApplicationTests.class);

    @Autowired
    TransportClient client;

    //ch1
    @Test
    public void createMapping () throws IOException {

        String indexName = "cms";

        IndicesAdminClient ac = client.admin().indices();
        CreateIndexRequestBuilder builder = ac.prepareCreate(indexName);

        //设置分片数量 这里我不是集群，不设置分片
        Settings.Builder setting = Settings.builder().put("number_of_shards",1);
        builder.setSettings(setting);

        //首先创建索引库
        CreateIndexResponse indexResponse = client.admin().indices()
                //这个索引库的名称不能包含大写字母
                .prepareCreate(indexName).setSettings(setting.build()).execute()
                .actionGet();
        logger.info("============>>>CreateIndex " + indexResponse.isShardsAcked()); //看是否创建成功

        //然后设置索引库结构
        String type = "article";
        XContentBuilder mapping = getMapping(type);
        PutMappingRequest mappingRequest = Requests.putMappingRequest(indexName)
                .type(type).source(mapping);
        PutMappingResponse putMappingResponse = client.admin().indices().putMapping(mappingRequest).actionGet();

        //看是否设定索引结构成功
        logger.info("============>>>putMappingResponse " + putMappingResponse.isAcknowledged()); //看是否创建成功
    }

    // 1.首先创建数据库结构
    private XContentBuilder getMapping(String indexType) throws IOException {

        XContentBuilder mapping = XContentFactory.jsonBuilder()
                .startObject().startObject(indexType).startObject("properties")
                .startObject("title").field("type","text")    //定义标题
                .field("store",true).field("analyzer","standard")
                .endObject()
                .startObject("body").field("type","text")     //定义内容列
                .field("store",true).field("analyzer","standard")
                .endObject()

                .endObject()    //属性结束
                .endObject()    //索引类型结束
                .endObject();

        return mapping;
    }


}
