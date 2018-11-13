package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayList;
import java.util.HashMap;

public class NodeType {
	public String type; // 类型
	public String tag; //语义标签
	//public String termText;
	public int start;
	public int end;
	public ArrayList<NodeType> lower; //用来得到树结构的下层，也就是这个规则是从哪里来的 
	
	public NodeType(WordType t){
		type = t.type;
	}

	public NodeType(String type, int start, int end, ArrayList<NodeType> lower) {
		this.type = type;
		this.start = start;
		this.end = end;
		this.lower = lower;
	}
	
	public void setTags(HashMap<String,String> ruleTag){
		for(NodeType n:lower){
			n.tag = ruleTag.get(n.type);  //设置标签语义
		}
	}

	@Override
	public String toString() {
		return "NodeType [type=" + type + ", start=" + start + ", end=" + end +", tag=" + tag 
				+ ", lower=" + lower + "]";
	}

}