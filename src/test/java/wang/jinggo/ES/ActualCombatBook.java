package wang.jinggo.ES;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.JinggoRedisTryApplicationTests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    //导入数据
    @Test
    public void createEsData(){
        String id = "20";
        IndexRequestBuilder indexRequestBuilder = client.prepareIndex("cms","article",id);
        //准备文档
        Map<String,String> source = new HashMap<>();
        source.put("title","标题");
        source.put("body","内容");
        indexRequestBuilder.setSource(source);
        IndexResponse response = indexRequestBuilder.execute().actionGet();
        logger.info("============>>>createEsData " + response.status().name());
    }

    //删除数据
    @Test
    public void delEsData(){
        String id = "20";
        DeleteResponse response = client.prepareDelete("cms","article",id).get();
        logger.info("============>>>delEsData " + response.status().name());
    }

    //更新数据
    @Test
    public void updateEsData() throws IOException, ExecutionException, InterruptedException {
        String id = "20";
        UpdateRequest request = new UpdateRequest();
        request.index("cms");
        request.type("article");
        request.id(id);
        request.doc(XContentFactory.jsonBuilder().startObject()
        .field("body","content").endObject());
        UpdateResponse response = client.update(request).get();
        logger.info("============>>>updateEsData " + response.getResult().name());
    }

    //多条件查询组合
    @Test
    public void termsData(){
        String id = "20";
        QueryBuilder qb = QueryBuilders.matchAllQuery();
        logger.info("============>>>查询所有 " + qb);

        String index = "cms";
        SearchResponse searchResponse = client.prepareSearch(index)
                .setQuery(qb).execute().actionGet();
        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits){
            logger.info("============>>>id " + hit.getId());
            Map<String,Object> result = hit.getSource();
            //键是列名，值是文档中该列的值
            for (final Map.Entry<String,Object> entry : result.entrySet()
                 ) {
                logger.info("============>>>termsData1 " + entry.getKey() + "  : " + entry.getValue());
            }
        }

        //基本的关键词查询
        String keyWords = "DNA";
        QueryStringQueryBuilder qb2 = new QueryStringQueryBuilder(keyWords);
        SearchResponse searchResponse2 = client.prepareSearch(index)
                .setQuery(qb2).execute().actionGet();

        //遍历查询结果
        SearchHits hits2 = searchResponse2.getHits();
        for (SearchHit hit2: hits2
             ) {
            logger.info("============>>>id2 " + hit2.getId());
            Map<String,Object> result2 = hit2.getSource();
            for (final Map.Entry<String,Object> entry2 : result2.entrySet()
                    ) {
                logger.info("============>>>termsData2 " + entry2.getKey() + "  : " + entry2.getValue());
            }
        }

        //限定返回的列数据
        SearchRequestBuilder restBuilder = client.prepareSearch(index).setQuery(qb);
        //限定只返回title 列的数据
        SearchResponse searchResponse3 = restBuilder.setFetchSource("title",null).execute().actionGet();

        //注：特可以使用client.prepareGet 方法返回指定文档
        GetResponse response3 = client.prepareGet("cms","article",id)
                .setFetchSource("title",null)
                .execute().actionGet();
        Map<String,Object> result3 = response3.getSourceAsMap();    //结果封装成map
        for (final Map.Entry<String,Object> entry3 : result3.entrySet()
                ) {
            logger.info("============>>>termsData3 " + entry3.getKey() + "  : " + entry3.getValue());
        }
    }

    //分页查询
    @Test
    public void termsPageData(){
        String id = "20";
        String index = "cms";
        int rows = 10;      //一页显示多少条搜索数据
        int offset = 0;     //开始行

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        searchRequestBuilder.setFrom(offset).setSize(rows);

        //实现高亮显示结果,首先指定高亮标签及哪些需要高亮，然后在符合条件的结果中得到高亮段
        //高亮标签
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        //指定高亮字段
        highlightBuilder.field("title");

        searchRequestBuilder.highlighter(highlightBuilder);

//        QueryBuilder qb = QueryBuilders.matchAllQuery();
//        SearchResponse searchResponse = client.prepareSearch(index)
//                .setQuery(qb).execute().actionGet();

        // 执行搜索,返回搜索响应信息
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        //另外，记录结果总数，用于计算总的页码数量
        SearchHits searchHits = searchResponse.getHits();
        long totalHost = searchHits.getTotalHits(); //得到总的页数

        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit: hits
                ) {
            StringBuffer stringBuffer = new StringBuffer();
            Text[] text = hit.getHighlightFields().get("title").getFragments();
            if (text != null) {
                for (Text str : text) {
                    stringBuffer.append(str.string());
                }
                hit.getSourceAsMap().put("id", id);
                hit.getSourceAsMap().put("title", stringBuffer.toString());
            }

            logger.info("============>>>stringBuffer " + stringBuffer.toString());
            logger.info("============>>>SearchHits " + hit.getSourceAsMap().toString());
        }
    }
}
