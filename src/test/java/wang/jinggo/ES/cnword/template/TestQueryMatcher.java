package wang.jinggo.ES.cnword.template;

import wang.jinggo.tutorial.es.cnword.QueryMatcher.QueryMatcher;

public class TestQueryMatcher {

	public static void main(String[] args) {
		QueryMatcher queryMatch = new QueryMatcher();
		String query ="种子质量鉴定";
		String template="[草种|粮种|种子][品质|质地|质量][鉴定]";
		
		queryMatch.add(query , template);

		String input = "粮种品质鉴定"; //用户输入
		
		String standardQuery = queryMatch.match(input);
		System.out.println("标准查询 "+standardQuery);
	}

}
