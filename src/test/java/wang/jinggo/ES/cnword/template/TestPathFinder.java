package wang.jinggo.ES.cnword.template;

import java.util.Deque;

import com.lietu.template.CnToken;
import com.lietu.template.PathFinder;
import com.lietu.template.QueryTemplate;

public class TestPathFinder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String question = //"化肥的说明书是什么";
				 //"农药的说明书有没有农药";
				"农药的说明书有没有";
		
		QueryTemplate grammar = new QueryTemplate();
		String ruleName = "询问农药";
		String right = "[农药:7|化肥:9]{产品}的[说明书|介绍][是什么|是]";
		//"[农药]的[说明书|介绍][是什么|有没有]";

		grammar.add(ruleName, right);

		ruleName = "询问化肥";
		right = "[农药]的[书籍|书][有没有|有吗]";

		grammar.add(ruleName, right);
		
		PathFinder pf = new PathFinder(grammar);
		Deque<CnToken> path = pf.getPath(question);
		
		if(path ==null){
			System.out.println("路径空");
			return;
		}
		for(CnToken t:path){
			System.out.println("路径 " + t);
		}
	}

}
