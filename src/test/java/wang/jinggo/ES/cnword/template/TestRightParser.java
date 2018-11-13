package wang.jinggo.ES.cnword.template;

import wang.jinggo.tutorial.es.cnword.template.RightParser;
import wang.jinggo.tutorial.es.cnword.template.Rule;
import wang.jinggo.tutorial.es.cnword.template.RightParser;

public class TestRightParser {

	public static void main(String[] args) {
		String right="[草种|粮种|种子][品质|质地|质量][鉴定]";
		String query="种子质量鉴定";
		Rule rule = RightParser.parse(right, query);
		System.out.println(rule);
	}

}
