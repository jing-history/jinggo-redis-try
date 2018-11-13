/*
 * Created on 2005-9-7
 *
 */
package wang.jinggo.tutorial.es.cnword.template;

import java.util.HashSet;

public class WordTokenInf {
	public String termText; //对应的文本
	public HashSet<WordType> types;  //类型
	public int start;
	public int end;

	public WordTokenInf(String word, int vertexFrom, int vertexTo, HashSet<WordType> types2) {
		start = vertexFrom;
		end = vertexTo;
		termText = word;
		types = types2;
	}

	public WordTokenInf(String word, int s, int e, String ruleName) {
		start = s;
		end = e;
		termText = word;
		HashSet<WordType> d = new HashSet<WordType>(1);
		d.add(new WordType(ruleName));
		types = d;
	}

	public String toString() {
		return "text:" + termText + " start:" + start + " end:" + end
				+ "--------types:" + types;
	}
}