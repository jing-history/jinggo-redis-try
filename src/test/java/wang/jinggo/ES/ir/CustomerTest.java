package wang.jinggo.ES.ir;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wang.jinggo.JinggoRedisTryApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyj
 * @description
 * @create 2018-12-03 10:09
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JinggoRedisTryApplication.class)
public class CustomerTest {


    private static final Logger LOGGER = LoggerFactory.getLogger(JinggoRedisTryApplication.class);

    @Autowired
    private TransportClient transportClient;
    public static String SEX;
    public static String LINKUP;
    public static String AGEGROUP;
    public static String INTENTION;
    public static String CUSLEVEL;
    public static String AREA;
    public static String HOMESTYLE;
    public static String TRADPHASE;
    public static String FOLLOWUP;
    public static String CNAME;
    public static String CUSTYPE;
    public static String CUSRELATION;
    public static String HOUSETYPE;
    public static String HOMESTATUS;
    public static String DOUBTDEAL;

    @Test
    public void createIndex(){

        Map<String, Object> jsonAll = new HashMap<String, Object>();
        Map<String, Object> jsonSex = new HashMap<String, Object>();
        jsonSex.put(CustomerKey.SEX,0);
        jsonSex.put("val","性别");
        jsonAll.put("sexMsg",jsonSex);

        Map<String, Object> jsonCname = new HashMap<String, Object>();
        jsonCname.put(CustomerKey.CNAME,"奥小马");
        jsonCname.put("val","称呼");
        jsonAll.put("cnameMsg",jsonCname);

        /*json.put(CustomerKey.SEX,0);
        json.put(CustomerKey.LINKUP,1);
        json.put(CustomerKey.AGEGROUP,"0");
        json.put(CustomerKey.INTENTION,"0");
        json.put(CustomerKey.CUSLEVEL,"0");
        json.put(CustomerKey.AREA,"0");
        json.put(CustomerKey.HOMESTYLE,"0");
        json.put(CustomerKey.TRADPHASE,"0");
        json.put(CustomerKey.FOLLOWUP,"0");
        json.put(CustomerKey.CNAME,"奥小马");
        json.put(CustomerKey.CUSTYPE,"0");
        json.put(CustomerKey.CUSRELATION,"0");
        json.put(CustomerKey.HOUSETYPE,"0");
        json.put(CustomerKey.HOMESTATUS,"0");
        json.put(CustomerKey.DOUBTDEAL,"0");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");*/

        CreateIndexRequestBuilder createIndexRequestBuilder = transportClient.admin()
                .indices().prepareCreate("ir_customer");  //创建称为ir_customer 的索引

        CreateIndexResponse response = createIndexRequestBuilder.execute().actionGet();
        LOGGER.info(String.valueOf(response.isAcknowledged()));

        /*IndexResponse response =transportClient.prepareIndex("ir_customer", "info").setSource(jsonAll).get();
        System.out.println("索引名称："+response.getIndex());
        System.out.println("类型："+response.getType());
        System.out.println("文档ID："+response.getId()); // 第一次使用是1
        System.out.println("当前实例状态："+response.status());*/
    }
}
