package wang.jinggo.tutorial.es.cnword.template;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie tree Node
 * 
 * @author luogang
 * 
 */
public class TrieNode {
	public String ruleName;  //规则名
	private Map<String, TrieNode> children = new HashMap<String, TrieNode>();

	public boolean isTerminal() {
		return ruleName!=null;
	}

	public void setValue(String terminal) {
		this.ruleName = terminal;
	}

	public Map<String, TrieNode> getChildren() {
		return children;
	}
	
	public TrieNode next(String e){
		return children.get(e);
	}

	public void addChild(String r,TrieNode n){
		children.put(r, n);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if(ruleName!=null)
			sb.append("可结束节点:"+ruleName.toString());
		if(children!=null){
			sb.append(" 孩子:"+children.toString());
		}
		return sb.toString();
	}

	public String getNodeValue() {
		return ruleName;
	}
}
