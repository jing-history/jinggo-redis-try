/*
 * Created on 2005-9-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package wang.jinggo.tutorial.es.cnword.template;

import java.util.HashSet;

/**
 * 
 * 有向边
 */
public class CnToken {
	public String termText;
	public HashSet<NodeType> types; //词的各种词性 
	public int start;
	public int end;
	
	public CnToken(int vertexFrom, int vertexTo, String word) {
		start = vertexFrom;
		end = vertexTo;
		termText = word;
	}
	
	public CnToken(int vertexFrom, int vertexTo, String word,HashSet<WordType> t) {
		start = vertexFrom;
		end = vertexTo;
		termText = word;
		HashSet<NodeType> typs = new HashSet<NodeType>(t.size());
		for(WordType wt:t){
			typs.add(new NodeType(wt));
		}
		types = typs;
	}
	
	public CnToken(int vertexFrom, int vertexTo, String word,NodeType n) {
		start = vertexFrom;
		end = vertexTo;
		termText = word;
		HashSet<NodeType> types = new HashSet<NodeType>(1);
		types.add(n);
		this.types = types;
	}
	
	public String toString() {
		return "text:" + termText + " start:" + start + " end:" + end+" types:"+types;
	}

}