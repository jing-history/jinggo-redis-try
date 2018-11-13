package wang.jinggo.ES.cnword.template;

import java.util.ArrayList;

import com.lietu.template.AdjList;
import com.lietu.template.QueryTemplate;
import com.lietu.template.GraphMatcher.MatchValue;

public class TestAdjList {

	public static void main(String[] args) {
		QueryTemplate grammar = new QueryTemplate();
		String ruleName ="询问农药";
		String right="[农药]的[说明书|介绍][是什么|有没有]";
		
		grammar.add(ruleName , right);
		
		ruleName ="询问化肥";
		right="[化肥]的[书籍|书][有没有|有吗]";
		
		grammar.add(ruleName , right);
		
		String question = //"化肥的说明书是什么";
				"农药的说明书有没有";
		AdjList g = grammar.getLattice(question);
		System.out.println("图 "+g);
		/*int offset=0;
		ArrayList<MatchValue> match = GraphMatcher.intersect(g, offset, grammar.rule);
		System.out.println("匹配结果 "+match);
		for(MatchValue m:match){
			WordType wt = new WordType(m.ruleName);
			CnToken newEdge = new CnToken(m.start,m.end,question.substring(m.start,m.end),wt);
			g.addEdge(newEdge);
		}
		System.out.println("词图 "+g);*/
	}

}
