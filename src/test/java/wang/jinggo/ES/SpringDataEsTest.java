package wang.jinggo.ES;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.dao.es.StSnapDao;
import wang.jinggo.domain.es.ElaStSnap;
import wang.jinggo.domain.es.MyBook;
import wang.jinggo.domain.es.SampleEntiry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 14:36
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataEsTest {

    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private StSnapDao stSnapDao;

    @Test
    public void queryEsData(){

        Pageable pageable = new PageRequest(0, 10, new Sort("studentName"));
        QueryBuilder qb1 = QueryBuilders.multiMatchQuery("电子", "studentName", "specialtyName");

        QueryBuilder qb2 = QueryBuilders.boolQuery()//
                .must(QueryBuilders.termQuery("schoolId", "0"))// 单个
                .must(QueryBuilders.termQuery("sex", "0"))//
                .must(QueryBuilders.termsQuery("specialtyId", "71", "72"))// 多选
                .must(QueryBuilders.termsQuery("educationId", "2", "3", "4"))//
                .must(QueryBuilders.matchQuery("addr", "海"));

            SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(qb1).withFilter(qb2).build();

        Page<ElaStSnap> pe1 = stSnapDao.search(searchQuery);
        System.out.println(pe1.getTotalElements());
        for (ElaStSnap sn : pe1.getContent()) {
        	System.out.println(sn);
        }
    }

    //索引文档
    @Test
    public void templateSaveData(){
        String id = "123456";
        SampleEntiry sampleEntiry = new SampleEntiry();
        sampleEntiry.setId(id);
        sampleEntiry.setMessage("some message");
        IndexQuery indexQuery = new IndexQueryBuilder().withId(sampleEntiry.getId()).withObject(sampleEntiry).build();
        template.index(indexQuery);
    }

    //索引多个文档
    @Test
    public void templateSaveListData(){
        List<IndexQuery> indexQueries = new ArrayList<>();

        String id = "123456";
        SampleEntiry sampleEntiry = new SampleEntiry();
        sampleEntiry.setId(id);
        sampleEntiry.setMessage("some message");
        IndexQuery indexQuery = new IndexQueryBuilder().withId(sampleEntiry.getId()).withObject(sampleEntiry).build();
        indexQueries.add(indexQuery);

        String id2 = "1234567";
        SampleEntiry sampleEntiry2 = new SampleEntiry();
        sampleEntiry2.setId(id2);
        sampleEntiry2.setMessage("some message2");
        IndexQuery indexQuery2 = new IndexQueryBuilder().withId(sampleEntiry.getId()).withObject(sampleEntiry).build();
        indexQueries.add(indexQuery2);

        template.bulkIndex(indexQueries);
    }

    //搜索实体
    @Test
    public void searchQueryEs(){
        String id = "123456";
        SearchQuery searchQuery = null;//new NativeSearchQueryBuilder().withQuery(quueryString(id).field("id"));
        Page<SampleEntiry> sampleEntiries = template.queryForPage(searchQuery, SampleEntiry.class);

        //执行索引结构
        template.putMapping(MyBook.class);
    }
}
