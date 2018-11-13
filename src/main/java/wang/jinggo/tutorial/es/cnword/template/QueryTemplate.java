package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import  wang.jinggo.tutorial.es.cnword.template.GraphMatcher.MatchValue;
import  wang.jinggo.tutorial.es.cnword.template.TernarySearchTrie.MatchRet;

/**
 * 2层Trie树实现的查询模板，在电子政务搜索中体现为百姓体
 * 输入串首先匹配三叉Trie树中的词,切分输入串并确定每个词可能的类型 然后根据词序列中每个词可能的类型查询模板树 最后根据句子模板归约到某一个查询串
 * 
 * TODO:如果内存装不下，就采用B树结构，把叶节点存到文件
 * @author luogang
 * @2013-10-31
 */
public class QueryTemplate {

	public TernarySearchTrie dic; // 基本词和对应的类型
	public HashMap<String,String> ruleTag;  //规则上的语义标签
	public Trie rule; // 句子模板类型Trie树

	//加载数组形式的文法内容
	public QueryTemplate(ArrayList<String> grammarContent) {
		dic = new TernarySearchTrie(); // 基本词和对应的类型
		rule = new Trie();
		ruleTag = new HashMap<String,String>();
		for (String line : grammarContent) {
			StringTokenizer st = new StringTokenizer(line, "=");
			String ruleName = st.nextToken();
			String right = st.nextToken();
			add(ruleName, right);
		}
	}
	
	public QueryTemplate(){
		dic = new TernarySearchTrie(); // 基本词和对应的类型
		rule = new Trie();
		ruleTag = new HashMap<String,String>();
	}
	
	public void add(String query,String template){
		Rule rightRule = RightParser.parse(template,query);
		rule.addRule(query, rightRule.rhs);
		dic.addWords(rightRule.words);
		ruleTag.putAll(rightRule.ruleTag);
	}
	
	public void add(String questionRule,Rule rightRule){
		rule.addRule(questionRule, rightRule.rhs);
		dic.addWords(rightRule.words);
		ruleTag.putAll(rightRule.ruleTag);
	}

	public AdjList getLattice(String sentence) { // 返回切分词图
		int sLen = sentence.length();// 字符串长度
		AdjList g = new AdjList(sLen);// 存储所有被切分的可能的词

		ArrayList<WordEntry> wordMatch = new ArrayList<WordEntry>();

		// 生成切分词图
		for (int i = 0; i < sLen;++i) {
			//System.out.println("i:"+i);
			boolean match = dic.matchAll(sentence, i, wordMatch);// 到词典中查询

			if (match)// 已经匹配上
			{
				for (WordEntry word : wordMatch) {
					//System.out.println(word);
					int end = i + word.word.length();// 词的开始位置
					g.addEdge(new CnToken(i,end, word.word,word.types));
				}
			}
		}
		
		//System.out.println("切分词图:"+g);
		
		//根据文法增加边
		boolean findNew = true;
		while (findNew) {
			findNew = false;
			for (int offset = 0; offset < sentence.length(); ++offset) {
				ArrayList<MatchValue> match = GraphMatcher.intersect(g, offset, rule);
				System.out.println("匹配结果 "+match);
				for(MatchValue m:match){
					//WordType wt = new WordType(m.ruleName);
					NodeType n = new NodeType(m.ruleName,m.start,m.end,m.posSeq);
					n.setTags(ruleTag);
					CnToken newEdge = new CnToken(m.start,m.end,sentence.substring(m.start,m.end),n);
					g.addEdge(newEdge);
				}
			}
		}
		
		return g;
	}
}
