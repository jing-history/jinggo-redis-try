package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 解析句子模板
 * @author luogang
 *
 */
public class RightParser {
	public final static String PositionSplit = "p-";

	/**
	 * 解析句子模板
	 * @param right 句子模板
	 * @param query 查询词
	 * @return  解析出来的规则
	 */
	public static Rule parse(String right, String query){
		if (right == null)
			return null;
		right = right.trim();
		
		Rule rule = new Rule();
		
		int senLen = right.length();// 首先计算出传入的这句话的字符长度
		int i = 0;// i是用来控制匹配的起始位置的变量

		while (i < senLen)// 如果i小于此句话的长度就进入循环
		{
			i = matchSpace(right, i); // 跳过空格
			String semName = query + PositionSplit + i; // 根据规则加上编号生成语义类的名字
			int offset = matchOptionWords(right, i,  semName,rule);
			if (offset > i) {
				i = offset + 1;
				rule.rhs.add(semName);
				continue;
			}
			offset = matchNormalWord(right, i);// 普通的词
			if (offset > i)// 已经匹配上
			{
				// 下次匹配点在这个词之后
				int start = i;
				int end = offset;
				String word = right.substring(start, end);
				//System.out.println("word: " + word);
				rule.addWord(word, semName);
				i = offset;
				rule.rhs.add(semName);
				continue;
			}
		}

		return rule;
	}

	public static int matchNormalWord(String sentence, int offset) {
		int i = offset;
		while (i < sentence.length()) {
			char c = sentence.charAt(i);
			if (c == '[' || c == '{') {
				break;
			}
			i++;
		}
		return i;
	}

	// 一个小的语义类
	public static int matchOptionWords(String sentence, int offset, String semName, Rule rule) {
		if (sentence.charAt(offset) == '[') {
			StringBuilder bracketContent = new StringBuilder(); // 方括号中的内容
			int i = offset + 1;
			boolean endWithBracket = false;
			while (i < sentence.length()) {
				char c = sentence.charAt(i);
				if (c == ']') {
					endWithBracket = true;
					break;
				}
				bracketContent.append(c);
				i++;
			}

			if (endWithBracket && bracketContent.length() > 0) {
				ArrayList<String> words = getOptionWords(bracketContent
						.toString());
				addWords(words, semName, rule);

				StringBuilder content = new StringBuilder();
				int newStart = i + 1;
				offset = matchTag(sentence, newStart, content);
				if (offset > newStart)// 匹配上规则
				{
					// 下次匹配点在这个词之后
					String tagName = content.toString();
					//System.out.println("标签名: " + tagName);
					rule.ruleTag.put(semName, tagName);
					i = offset;
				}

				return i;
			}
		}

		return offset;
	}

	private static int matchTag(String sentence, int offset,
			StringBuilder bracketContent) {
		if (offset >= sentence.length())
			return offset;
		// System.out.println("TO match Tag: " + sentence.charAt(offset));
		if (sentence.charAt(offset) == '{') {
			bracketContent.setLength(0); // 方括号中的内容
			int i = offset + 1;
			boolean endWithBracket = false;
			while (i < sentence.length()) {
				char c = sentence.charAt(i);
				if (c == '}') {
					endWithBracket = true;
					break;
				}
				bracketContent.append(c);
				i++;
			}

			if (endWithBracket && bracketContent.length() > 0) {
				return i;
			}
		}

		return offset;
	}

	public static void addWords(ArrayList<String> words,
			String semName, Rule rule) {
		for (String w : words) {
			String type = semName; // 语义类的名字
			rule.addWord(w, type); // 终结符放入词典三叉树
		}
	}

	// 得到可选终结符和非终结符
	public static ArrayList<String> getOptionWords(String t) {
		// System.out.println("input " + t);
		ArrayList<String> words = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(t, "|"); // |分隔
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			//System.out.println("get OptionWord " + s);
			words.add(s);
		}
		return words;
	}

	//匹配空格
	public static int matchSpace(String sentence, int offset) {
		int first = offset;

		for (; first < sentence.length(); first++)
			if (!Character.isWhitespace(sentence.charAt(first)))
				break;

		return first;
	}

}
