package wang.jinggo.web.manage;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import wang.jinggo.common.constant.ESConstrant;
import wang.jinggo.common.vo.EsCount;
import wang.jinggo.common.vo.EsInfo;
import wang.jinggo.common.vo.PageVo;
import wang.jinggo.common.vo.Result;
import wang.jinggo.dao.es.BookDao;
import wang.jinggo.domain.es.Book;
import wang.jinggo.exception.XbootException;
import wang.jinggo.pojo.ESQueryVO;
import wang.jinggo.pojo.ListDesc;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.util.ESUtil;
import wang.jinggo.util.PageUtil;
import wang.jinggo.util.ResultUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 18:07
 **/
@Slf4j
@RestController
@Api(description = "Elasticsearch信息接口")
@RequestMapping("/xboot/es")
@Transactional
public class EsController {

    @Value("${xboot.elasticsearch.nodeClient}")
    private String ES_NODE_CLIENT;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TransportClient client;

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private BookDao bookDao;

    @RequestMapping(value = "/searchWeb",method = RequestMethod.GET)
    @ApiOperation(value = "ES智能匹配查询")
    public Result<Object> searchWeb(@RequestParam(required = true) ESQueryVO esQueryVO, @ModelAttribute PageVo pageVo){

        String index = ESConstrant.INDEXNAME;// 索引名
        Pageable pageable = PageUtil.initPage(pageVo);

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch(index);
        QueryBuilder qb = ESUtil.getQueryBuilder(esQueryVO.getQuery());
        searchRequestBuilder = searchRequestBuilder.setQuery(qb);
        // addFields 方法没有
        //searchRequestBuilder.addFields("repname", "docType","folder", "repsubunit","contentsShort");
        // 分页应用
        searchRequestBuilder.setFrom(pageable.getPageNumber()).setSize(pageable.getPageSize());
        // 设置高亮显示列
        //高亮标签
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //指定高亮字段
        highlightBuilder.field(ESConstrant.TITLE);
        int fragmentSize = 120;
        highlightBuilder.field(ESConstrant.BODY3,fragmentSize);
        highlightBuilder.preTags("<span style=\"color:red\">");
        highlightBuilder.postTags("</span>");
        searchRequestBuilder.highlighter(highlightBuilder);

        SearchResponse response = null;
        ListDesc desc = new ListDesc();
        // 执行搜索,返回搜索响应信息
        response = searchRequestBuilder.execute().actionGet();
        // 获取搜索的文档结果
        SearchHits searchHits = response.getHits();
        long totalHits = searchHits.getTotalHits();  //得到结果总数

        for (SearchHit hit : searchHits) {
            HashMap<String,String> row = new HashMap<String,String>();

            // 获取对应的高亮域
            Map<String, HighlightField> _highlighting = hit.highlightFields();

            Map<String, SearchHitField> map = hit.getFields(); // 键是列名，值是文档中该列的值
            // 从设定的高亮域中取得指定域
            _highlighting.get(ESConstrant.TITLE);
            map.get("folder");
            map.get("repname");
            map.get("docType");
            map.get("repsubunit");
            ESUtil.getBody(_highlighting.get(ESConstrant.BODY3));
        }


        return new ResultUtil<Object>().setData(null);
    }

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "获取es状态")
    public Result<EsInfo> getAllByPage(){
        String healthUrl="http://"+ES_NODE_CLIENT+"/_cluster/health";
        String countUrl="http://"+ES_NODE_CLIENT+"/_count";

        String healthResult= HttpUtil.get(healthUrl);
        String countResult=HttpUtil.get(countUrl);

        if(StrUtil.isBlank(healthResult)||StrUtil.isBlank(countResult)){
            throw new XbootException("连接ES失败，请检查ES运行状态");
        }

        EsInfo esInfo=new EsInfo();
        EsCount esCount=new EsCount();

        try {
            esInfo=new Gson().fromJson(healthResult,EsInfo.class);
            esCount=new Gson().fromJson(countResult,EsCount.class);
            esInfo.setCount(esCount.getCount());
        }catch (Exception e){
            e.printStackTrace();
            throw new XbootException("获取ES信息出错");
        }
        return new ResultUtil<EsInfo>().setData(esInfo);
    }

    /**
     * 注意：spring data 使用简单，不支持复杂查询和统计分析，
     * 如果需要复杂的统计功能，最好使用Rest方式完成
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/book/rest/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "es book例子rest 方式根据id获取内容")
    public Result<Book> restBookById(@PathVariable String id) throws IOException {
        Book book = null;
        Map<String,Object> paras = new HashMap<>();
        paras.put("id", id);

        //rest 请求
        String str = restTemplate.getForObject("http://192.168.1.111:9200/product/book/{id}", String.class, paras);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser parser = factory.createParser(str);

        //对返回的json的source 字段取值
        JsonNode root = mapper.readTree(parser);
        JsonNode sourceNode = root.get("_source");
        //将source 字段映射到book 对象
        book = mapper.convertValue(sourceNode, Book.class);
        return new ResultUtil<Book>().setData(book);
    }

    @RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
    @ApiOperation(value = "es book例子根据id获取内容")
    public Result<Book> getBookById(@PathVariable String id){
        Optional<Book> opt = bookDao.findById(id);
        Book book = opt.get();
        return new ResultUtil<Book>().setData(book);
    }

    @RequestMapping(value = "/book/search/{key}",method = RequestMethod.GET)
    @ApiOperation(value = "es book例子根据key查询")
    public Result<List> searchBookByKey(@PathVariable String key){
        //测试全文搜索
        List<Book> list = bookDao.getByMessage(key);
        return new ResultUtil<List>().setData(list);
    }

    @RequestMapping(value = "/book/search/{key}/{page}",method = RequestMethod.GET)
    @ApiOperation(value = "es book例子根据key带分页查询")
    public Result<List> searchBookByKeyAndPage(@PathVariable int page, @PathVariable String key){
        int num = 5;
        PageRequest request = PageRequest.of(page, num);
        //全文搜索翻页
        Page<Book> pages = null;//bookDao.getByMessage(key, request);
        long total = pages.getTotalElements();
        long totalPage = pages.getTotalPages();
        List<Book> list = pages.getContent();
        return new ResultUtil<List>().setData(list);
    }
}
