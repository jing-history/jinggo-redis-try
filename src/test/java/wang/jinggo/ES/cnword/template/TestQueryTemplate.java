package wang.jinggo.ES.cnword.template;

import wang.jinggo.tutorial.es.cnword.template.*;

import java.util.ArrayList;
import java.util.Map.Entry;


/**
 * @author luogang
 * @2013-10-17
 */
public class TestQueryTemplate {

	public static void main(String[] args) throws Exception {
		//testGrammar();
		//testGrammarMatch();
		//testParse();
		//testIterator();
		//testContentIterator();
		//testTag();
		//testAdd();
		//testExplain();
		//testResult();
		//testSave();
		//testLoad();
		testSegGraph();
	}

	private static void testAdd() {
		QueryTemplate grammar = new QueryTemplate();
		String query ="种子质量鉴定";
		String right="[草种|粮种|种子][品质|质地|质量][鉴定]";
		
		grammar.add(query , right);
		Trie trie = grammar.rule;
		for(StackValue s :trie){
			System.out.println("值 "+s);
		}
		
		for(Entry<String, String> e :grammar.ruleTag.entrySet()){
			System.out.println("标签 "+e);
		}
	}

	private static void testSegGraph() {
		String query = getQuery();
		System.out.println("查询词 "+query);
	}
	
	private static String getQuery(){
		QueryTemplate grammar = new QueryTemplate();
		String query ="种子质量鉴定";
		String right="[草种|粮种|种子][品质|质地|质量][鉴定]";
		
		grammar.add(query , right);
		
		query ="基本农田划区定界";
		right="[吃饭田|保命田|耕地|基本农田][划分|划区][界限确定|界线确定|定界]";
		
		grammar.add(query , right);
		
		String question = "保命田划分定界";
				//"粮种品质鉴定";
		AdjList g = grammar.getLattice(question);
		System.out.println("图 "+g);
		
		CnTokenLinkedList ll = g.edges(0);

		//IndexMinPQ<Integer> indexMinPQ = new IndexMinPQ<Integer>(question.length());
		
		//int distance = 1;
		for(CnToken t :ll){
			if(t.end==question.length()){
				return t.types.iterator().next().type;//
			}
			//indexMinPQ.insert(t.end,distance);
		}

		return null;
	}

	public static void testContentIterator(){
		ArrayList<String> grammarContent = new ArrayList<String>();
		grammarContent.add("<询问13>=你们公司[<叫什么名字:10>|<是什么公司:2472>]");
		grammarContent.add("<叫什么名字:10>=[的名称|的名字|叫什么名字]");
		grammarContent.add("<是什么公司:2472>=[是什么类型的|干什么的|是什么公司]");
		QueryTemplate grammar = new QueryTemplate(grammarContent);
		Trie trie = grammar.rule;
		for(StackValue s :trie){
			System.out.println("值 "+s);
		}
	}
	
	public static void testTag(){
		ArrayList<String> grammarContent = new ArrayList<String>();
		//可以在模板中给一个词设置语义标签
		grammarContent.add("<询问产品>=[农药:7|化肥:9]{产品}的[说明书|介绍][是什么|是]");
		QueryTemplate grammar = new QueryTemplate(grammarContent);
		Trie trie = grammar.rule;
		for(StackValue s :trie){
			System.out.println("值 "+s);
		}
		
		for(Entry<String, String> e :grammar.ruleTag.entrySet()){
			System.out.println("标签 "+e);
		}
		
		String question = "化肥的说明书是什么";
		//String ruleName = grammar.parse(question);
		//System.out.println("解析树  " + grammar.track);
		//System.out.println("规则名: " + ruleName);
	}
}
