package wang.jinggo.ES.cnword.search;

import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

public class PhraseQueryTest {

	public static void main(String[] args) {
		String keyWords = "自2006年8月2日北京市";
		String field = "body"; //"title"
		MatchPhraseQueryBuilder pqBody = QueryBuilders.matchPhraseQuery(field, keyWords);
		
		field = "title";
		MatchPhraseQueryBuilder pqTitle = QueryBuilders.matchPhraseQuery(field, keyWords);
		
		QueryStringQueryBuilder fuzzyQb = new QueryStringQueryBuilder(keyWords);
		
		QueryBuilder qb = QueryBuilders.boolQuery().should(pqBody).should(pqTitle).should(fuzzyQb);
		System.out.println(qb);
	}

}
