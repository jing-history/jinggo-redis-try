package wang.jinggo.tutorial.es.cnword.template;

public class NextInput {// 有限状态机中的下一个输入
	int end; // 词的结束位置,词图中的下一个状态编号
	NodeType type; // 经过的类型,规则树中下一个状态的依据
	String term;
	
	public NextInput(int e, NodeType t,String text) {
		this.end = e;
		this.type = t;
		term = text;
	}
}
