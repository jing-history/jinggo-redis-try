package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayList;

/**
 * 用来遍历句子模板类型Trie树
 * @author luogang
 *
 */
public class StackValue {
	public TrieNode node;
	public ArrayList<String> rhs;

	public StackValue(TrieNode n, ArrayList<String> r) {
		node = n;
		rhs = r;
	}

	public String toString() {
		return node.toString() + " rhs:" + rhs;
	}
}
