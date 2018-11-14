package wang.jinggo.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import wang.jinggo.common.constant.ESConstrant;

/**
 * Elasticsearch 通用工具类
 * @author wangyj
 * @description
 * @create 2018-11-14 10:40
 **/
public class ESUtil {

    public static QueryBuilder getQueryBuilder(String qString){
        if(StringUtils.isEmpty(qString)){
            return QueryBuilders.matchAllQuery();
        }
        //集成短语查询
        MatchPhraseQueryBuilder pqTitle = QueryBuilders.matchPhraseQuery(ESConstrant.TITLE, qString);
        MatchPhraseQueryBuilder pqTitle2 = QueryBuilders.matchPhraseQuery(ESConstrant.TITLE2, qString);
        MatchPhraseQueryBuilder pqBody = QueryBuilders.matchPhraseQuery(ESConstrant.BODY, qString);
        MatchPhraseQueryBuilder pqBody2 = QueryBuilders.matchPhraseQuery(ESConstrant.BODY2, qString);

        QueryStringQueryBuilder fuzzyQb = new QueryStringQueryBuilder(qString)
                .field(ESConstrant.TITLE2).field(ESConstrant.BODY2).
                        field(ESConstrant.TITLE).field(ESConstrant.BODY);
        QueryBuilder qb = QueryBuilders.boolQuery().should(pqTitle2).
                should(pqTitle).should(pqBody2)
                .should(pqBody).should(fuzzyQb);
        return qb;
    }

    public static String getBody(HighlightField bodyField) {
        StringBuilder title = new StringBuilder();
        // 取得定义的高亮标签
        Text[] titleTexts = bodyField.fragments();
        // 为title串值增加自定义的高亮标签
        for (Text text : titleTexts) {
            title.append(text);
        }
        return title.toString();
    }

    //布查询来合并多个查询条件
    public static void addShould(BoolQueryBuilder qb, String query, String singleField,String wordField){

        if(StringUtils.isNotEmpty(query)){
            MatchPhraseQueryBuilder singleQB = QueryBuilders.matchPhraseQuery(singleField,query);   //子查询
            MatchPhraseQueryBuilder wordQB = QueryBuilders.matchPhraseQuery(wordField,query);   //子查询

            QueryBuilder currentQB = QueryBuilders.boolQuery().should(singleQB).should(wordQB);

        }
    }

    //搜索结果排序
    public static void sortBody(){
        Sort sort = new Sort(new SortField("date",SortField.Type.STRING,true));
     //   ScoreDoc[] hits = search,search(query,null,1000,sort).scoreDocs;
    }

    //类似查找 MLT 相关类型查找
    public static void searchMLT(){
        String field[] = new String[]{"title", "desc"};
        String likeTexts[] = new String[]{"Once upon a time"};
        MoreLikeThisQueryBuilder.Item[] likeItems = new MoreLikeThisQueryBuilder.Item[]{new MoreLikeThisQueryBuilder.Item()};
        MoreLikeThisQueryBuilder queryBuilder = new MoreLikeThisQueryBuilder(field,likeTexts,likeItems);
    }
}
