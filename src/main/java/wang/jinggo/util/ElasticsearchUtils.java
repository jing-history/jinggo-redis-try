package wang.jinggo.util;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.jinggo.pojo.EsPage;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @description
 * @create 2018-10-18 16:15
 **/
@Component
public class ElasticsearchUtils {

    private final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchUtils.class);

    @Autowired
    private TransportClient transportClient;

    private TransportClient client;

    @PostConstruct
    public void init() {
        client = this.transportClient;
    }

    /**
     * 数据添加/更新，正定ID
     *
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表
     * @param id         数据ID
     * @return
     */
    public String addData(Object jsonObject, String index, String type, String id) {
        IndexResponse response = client.prepareIndex(index, type, id).setSource(new Gson().toJson(jsonObject), XContentType.JSON).get();
        LOGGER.info("addData response status:{},id:{}", response.status().getStatus(), response.getId());
        return response.getId();
    }

    /**
     * 数据批量添加/更新，正定ID
     *
     * @param objList 要增加的数据
     * @param index   索引，类似数据库
     * @param type    类型，类似表
     * @param ids     数据ID
     * @return
     */
    public void addBulkData(List<Object> objList, String index, String type, List<String> ids) {
        if (objList == null || ids == null) {
            LOGGER.warn("data is null");
        }
        BulkRequestBuilder bulk = client.prepareBulk();
        for (int i = 0; i < objList.size(); i++) {
            bulk.add(client.prepareIndex(index, type, ids.get(i)).setSource(new Gson().toJson(objList.get(i)), XContentType.JSON));
        }
        BulkResponse bulkResponse = bulk.get();
        if (bulkResponse.hasFailures()) {
            LOGGER.error("addBulkData error:{}", bulkResponse.buildFailureMessage());
        } else {
            LOGGER.info("addBulkData response status:{}", bulkResponse.status().getStatus());
        }
    }

    /**
     * 通过ID删除数据
     *
     * @param index 索引，类似数据库
     * @param type  类型，类似表
     * @param id    数据ID
     */
    public void deleteDataById(String index, String type, String id) {
        DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
        LOGGER.info("deleteDataById response status:{},id:{}", response.status().getStatus(), response.getId());
    }


    /**
     * 批量删除数据
     *
     * @param index 索引，类似数据库
     * @param type  类型，类似表
     * @param ids   数据ID
     */
    public void deleteBulkData(String index, String type, List<String> ids) {
        if (ids == null) {
            LOGGER.warn("ids is null");
        }
        BulkRequestBuilder bulk = client.prepareBulk();
        for (int i = 0; i < ids.size(); i++) {
            bulk.add(client.prepareDelete(index, type, ids.get(i)));
        }
        BulkResponse bulkResponse = bulk.execute().actionGet();
        if (bulkResponse.hasFailures()) {
            LOGGER.error("delBulkData error:{}", bulkResponse.buildFailureMessage());
        } else {
            LOGGER.info("delBulkData response status:{}", bulkResponse.status().getStatus());
        }
    }

    /**
     * 删除type所有数据
     *
     * @param index 索引，类似数据库
     * @param type  类型，类似表
     */
    public void deleteDataByType(String index, String type) {
        DeleteByQueryAction.INSTANCE.newRequestBuilder(client)
                .source(index)
                .filter(QueryBuilders.boolQuery().must(QueryBuilders.typeQuery(type)))
                .get();
        LOGGER.info("deleteDataByType response index:{},type{}", index, type);
    }

    /**
     * 通过ID 更新数据
     *
     * @param jsonObject 要增加的数据
     * @param index      索引，类似数据库
     * @param type       类型，类似表
     * @param id         数据ID
     * @return
     */
    public void updateDataById(Object jsonObject, String index, String type, String id) {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(index).type(type).id(id).doc(XContentType.JSON, new Gson().toJson(jsonObject));
        client.update(updateRequest);
    }

    /**
     * 通过ID获取数据
     *
     * @param index  索引，类似数据库
     * @param type   类型，类似表
     * @param id     数据ID
     * @param fields 需要显示的字段，逗号分隔（缺省为全部字段）
     * @return
     */
    public Map<String, Object> searchDataById(String index, String type, String id, String fields) {
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        if (StringUtils.isNotEmpty(fields)) {
            getRequestBuilder.setFetchSource(fields.split(","), null);
        }
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        return getResponse.getSource();
    }

    public List<Map<String, Object>> searchKnowledgeTitle(String index, String type, String keyword, int size) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        searchRequestBuilder.setTypes(type);
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);

        searchRequestBuilder.setFetchSource("title", null);

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must(QueryBuilders.matchQuery("title", keyword));

        // 高亮（xxx=111,aaa=222）
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        // 设置高亮字段
        highlightBuilder.field("title");
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        searchRequestBuilder.highlighter(highlightBuilder);

        searchRequestBuilder.setQuery(boolQuery);

        // 分页应用
        searchRequestBuilder.setFrom(0).setSize(size);

        LOGGER.info("\n{}", searchRequestBuilder);

        // 执行搜索,返回搜索响应信息
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;
        LOGGER.info(searchResponse.toString());
        LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200 && length > 0) {
            List<Map<String, Object>> sourceList = new ArrayList<>();
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                StringBuffer stringBuffer = new StringBuffer();
                searchHit.getSourceAsMap().put("id", searchHit.getId());
                Text[] text = searchHit.getHighlightFields().get("title").getFragments();
                if (text != null) {
                    for (Text str : text) {
                        stringBuffer.append(str.string());
                    }
                    searchHit.getSourceAsMap().put("title", stringBuffer.toString());
                }
                sourceList.add(searchHit.getSourceAsMap());
            }
            return sourceList;
        }
        return null;
    }

    public EsPage searchKnowledge(String index, String type, String keyword, int pageNo, int pageSize) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        searchRequestBuilder.setTypes(type);
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);
        searchRequestBuilder.setFetchSource(null, new String[]{"htmlContent", "fileList", "categoryIds", "sort"});

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        MultiMatchQueryBuilder multiQuery = QueryBuilders.multiMatchQuery(keyword, "title", "content").field("title", 10);
//        boolQuery.should(QueryBuilders.matchQuery("title", keyword).boost(10.0f));
//        boolQuery.should(QueryBuilders.matchQuery("content", keyword));
        boolQuery.must(multiQuery);
        // 高亮（xxx=111,aaa=222）
        HighlightBuilder highlightBuilder = new HighlightBuilder();

        // 设置高亮字段
        highlightBuilder.field("title");
        highlightBuilder.field("content");
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        highlightBuilder.fragmentSize(30);
        highlightBuilder.numOfFragments(1);
        highlightBuilder.noMatchSize(30);
        searchRequestBuilder.highlighter(highlightBuilder);

        searchRequestBuilder.setQuery(boolQuery);

        // 分页应用
        searchRequestBuilder.setFrom((pageNo - 1) * pageSize).setSize(pageSize);

        LOGGER.info("\n{}", searchRequestBuilder);

        // 执行搜索,返回搜索响应信息
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;
        LOGGER.info(searchResponse.toString());
        LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200 && length > 0) {
            List<Map<String, Object>> sourceList = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                StringBuffer stringBuffer1 = new StringBuffer();
                searchHit.getSourceAsMap().put("id", searchHit.getId());
                ids.add(searchHit.getId());
                Text[] text1 = searchHit.getHighlightFields().get("title").getFragments();
                if (text1 != null) {
                    for (Text str : text1) {
                        stringBuffer1.append(str.string());
                    }
                    searchHit.getSourceAsMap().put("title", stringBuffer1.toString());
                }
                Text[] text2 = searchHit.getHighlightFields().get("content").getFragments();
                StringBuffer stringBuffer2 = new StringBuffer();
                if (text2 != null) {
                    for (Text str : text2) {
                        stringBuffer2.append(str.string());
                    }
                    searchHit.getSourceAsMap().put("content", stringBuffer2.toString());
                }
                sourceList.add(searchHit.getSourceAsMap());
            }
            EsPage page = new EsPage();
            page.setIds(ids);
            page.setRecordCount(totalHits);
            page.setRecordList(sourceList);
            return page;
        }
        return null;
    }

    public EsPage pageKnowledge(String index, String type, String categoryId, int pageNo, int pageSize) {
        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        searchRequestBuilder.setTypes(type);
        searchRequestBuilder.setSearchType(SearchType.QUERY_THEN_FETCH);
        searchRequestBuilder.setFetchSource(null, new String[]{"htmlContent", "fileList", "categoryIds", "sort"});

        TermQueryBuilder query = QueryBuilders.termQuery("categoryIds", categoryId);
        searchRequestBuilder.setQuery(query);

        // 分页应用
        searchRequestBuilder.setFrom((pageNo - 1) * pageSize).setSize(pageSize);
        searchRequestBuilder.addSort("sort", SortOrder.ASC);

        LOGGER.info("\n{}", searchRequestBuilder);

        // 执行搜索,返回搜索响应信息
        SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();

        long totalHits = searchResponse.getHits().totalHits;
        long length = searchResponse.getHits().getHits().length;
        LOGGER.info(searchResponse.toString());
        LOGGER.info("共查询到[{}]条数据,处理数据条数[{}]", totalHits, length);

        if (searchResponse.status().getStatus() == 200 && length > 0) {
            List<Map<String, Object>> sourceList = new ArrayList<>();
            List<String> ids = new ArrayList<>();
            for (SearchHit searchHit : searchResponse.getHits().getHits()) {
                searchHit.getSourceAsMap().put("id", searchHit.getId());
                ids.add(searchHit.getId());
                sourceList.add(searchHit.getSourceAsMap());
            }
            EsPage page = new EsPage();
            page.setIds(ids);
            page.setRecordCount(totalHits);
            page.setRecordList(sourceList);
            return page;
        }
        return null;
    }
}
