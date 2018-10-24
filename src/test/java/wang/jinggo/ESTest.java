package wang.jinggo;

import com.google.gson.Gson;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequest;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.util.ElasticsearchUtils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
    @Test
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

    //假设json 文件中存储了索引的映射、设置，用下面代码设定索引库结构
    @Test
    public void setESmappingByJson() throws IOException {
        CreateIndexRequestBuilder createIndexRequestBuilder = transportClient.admin()
                .indices().prepareCreate("test1");  //创建称为test1 的索引
        Path json_path = Paths.get("C:/Xmp");
        String mapping_json = new String(Files.readAllBytes(json_path));
        createIndexRequestBuilder.addMapping("my_mapping",mapping_json);
        CreateIndexResponse response = createIndexRequestBuilder.execute().actionGet();
    }

    public void putMapping() throws IOException {
        String indexType = "String";
        XContentBuilder mapping = XContentFactory.jsonBuilder().startObject()
                .startObject(indexType)
                .startObject("properties")
                .startObject("repname").field("type","string")
                .field("store","yes").field("analyzer","standard")  //词
                .endObject()

                .startObject("repnameS").field("type","string")
                .field("store","yes").field("analyzer","standard")  //字
                .endObject()

                .startObject("path").field("type","string")
                .field("store","yes").field("analyzer","standard")
                .endObject()

                .startObject("contents").field("type","string")
                .field("store","yes").field("analyzer","cn")  //词
                .endObject()

                .startObject("contentsS").field("type","string")
                .field("store","yes").field("analyzer","standard")  //字
                .endObject()

                .endObject() //end properties
                .endObject() //end indexType
                .endObject();

        // 使用 IndicesAdminClient putMapping 方法设定索引库结构
        PutMappingRequest mappingRequest = Requests.putMappingRequest("indexName").type("type").source(mapping);
        PutMappingResponse putMappingResponse = transportClient.admin().indices().putMapping(mappingRequest).actionGet();

        //使用 IndicesAdminClient 删除索引
        IndicesAdminClient admin = transportClient.admin().indices();
        admin.prepareDelete("indexName").execute().actionGet().isAcknowledged();

        //使用 IndicesAdminClient.prepareCreate 方法设置好创建索引需要的参数
        CreateIndexResponse indexResponse = transportClient.admin().indices()
                .prepareCreate("testindex").setSettings(Settings.EMPTY).execute()
                .actionGet();
        logger.info(String.valueOf(indexResponse.isAcknowledged()));
    }

    @Test
    public void addESdata() {
        Map<String,String> data = new HashMap<>();
        data.put("user","jinggo");
        data.put("postDate","2018-10-23");
        data.put("message","你要干啥子吗!!!");

        Gson gson = new Gson();
        String json = gson.toJson(data);

        IndexRequestBuilder indexRequestBuilder = transportClient.prepareIndex("test1","mydata")
                .setSource(json);
        IndexResponse response = indexRequestBuilder.execute().actionGet();
        logger.info("================>>> " + response.toString());
    }

    //批量插入数据
    @Test
    public void addBatchData() throws IOException {
        Map<String,String> data = new HashMap<>();
        data.put("user","jinggo");
        data.put("postDate","2018-10-23");
        data.put("message","try out ES1111111!!!");
        BulkRequestBuilder bulkRequestBuilder = transportClient.prepareBulk();
        bulkRequestBuilder.add(transportClient.prepareIndex("test1","mydata").setSource(data));
        BulkResponse bulkResponse = bulkRequestBuilder.execute().actionGet();
        if(bulkResponse.hasFailures()){ //异常处理
            logger.error(bulkResponse.buildFailureMessage());
        }
    }

    //删除数据
    @Test
    public void deleteResponse() throws IOException {
        DeleteResponse response = transportClient.prepareDelete("test1","mydata","lEaqnmYB98-2md4ziPtV").get();
    //   logger.info(String.valueOf(response.isFragment()));
    }

    //修改数据
    @Test
    public void updateResponse() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("test1");   //索引名
        updateRequest.type("mydata");   //类型
        updateRequest.id("lEZCm2YB98-2md4zfBBK");   //id
        updateRequest.doc(jsonBuilder().startObject().field("message","魔兽世界！！！").endObject());
        transportClient.update(updateRequest).get();
    }

    //分析器
    @Test
    public void analyzeWords() throws Exception {
        AnalyzeRequest analyzeRequest = new AnalyzeRequest();
        analyzeRequest.text("魔兽世界");
        ActionFuture<AnalyzeResponse> analyzeResponseActionFuture = transportClient.admin().indices().analyze(analyzeRequest);
        List<AnalyzeResponse.AnalyzeToken> analyzeTokens = analyzeResponseActionFuture.actionGet().getTokens();
        for (AnalyzeResponse.AnalyzeToken analyzeToken: analyzeTokens) {
            logger.info(analyzeToken.getTerm());
        }
    }

    @Test
    public void queryDSL() throws Exception {
        String keyWords = "value1";   //查询词
        TermQueryBuilder qb = new TermQueryBuilder("title",keyWords);
        //也可以使用
        //QueryBuilder queryBuilder = QueryBuilders.termQuery("title",keyWords);
        String index = "test1"; //索引名
        SearchResponse searchResponse = transportClient.prepareSearch(index).setQuery(qb).execute().actionGet();
    }

    //基本查询
    @Test
    public void testSearch() throws Exception {
        MatchAllQueryBuilder qb = QueryBuilders.matchAllQuery();
        String index = "bookdb_index";

        SearchResponse searchResponse =
                transportClient.prepareSearch(index).setQuery(qb).execute().actionGet();

        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            System.out.println("id "+hit.getId()); // 文档id
            Map<String, Object> result = hit.getSource(); // 键是列名，值是文档中该列的值
            for (final Map.Entry<String, Object> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    @Test
    public void testSearchFetchSource() throws Exception {

        String keyWords = "Elasticsearch";
        QueryStringQueryBuilder qb = new QueryStringQueryBuilder(keyWords);

        String index = "bookdb_index";

        SearchResponse searchResponse = transportClient.prepareSearch(index)
                .setQuery(qb).execute().actionGet();

        SearchHits hits = searchResponse.getHits();
        for (SearchHit hit : hits) {
            System.out.println("id "+hit.getId()); // 文档id
            Map<String, Object> result = hit.getSource(); // 键是列名，值是文档中该列的值
            for (final Map.Entry<String, Object> entry : result.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }

        transportClient.close();
    }
}
