package wang.jinggo.tutorial.es.cnword.template;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

/**
 * 词性标注 Trie tree
 * 
 * @author luogang
 * 
 */
public class Trie implements Iterable<StackValue> {
	public TrieNode rootNode = new TrieNode(); // 根节点
	int nodeNum=1;

	// 放入键/值对
	public void addRule(String ruleName, ArrayList<String> rhs) {
		TrieNode currNode = rootNode; // 当前节点
		for (int i = 0; i < rhs.size(); ++i) { // 从前往后找键中的类型
			String c = rhs.get(i);
			Map<String, TrieNode> map = currNode.getChildren();
			currNode = map.get(c); // 向下移动当前节点
			if (currNode == null) {
				currNode = new TrieNode();
				nodeNum++;
				map.put(c, currNode);
			}
		}
		currNode.setValue(ruleName); // 设置成可以结束的节点
	}
	
	// 根据键查找对应的值
	public boolean find(ArrayList<String> key) {
		TrieNode currNode = rootNode; // 当前节点
		for (int i = 0; i < key.size(); ++i) { // 从前往后找
			String c = key.get(i);
			currNode = currNode.getChildren().get(c);// 向下移动当前节点
			if (currNode == null) {
				return false;
			}
		}
		if (currNode.isTerminal()) {
			return true;
		}
		return false;
	}

	public Set<String> edges(TrieNode currNode) {
		return currNode.getChildren().keySet();
	}

	public Trie() {
	}

	public TrieNode next(TrieNode currNode, String edge) {
		return currNode.getChildren().get(edge);
	}

	private final class TrieIterator implements Iterator<StackValue> { // 用于迭代的类
		Stack<StackValue> stack = new Stack<StackValue>(); // 记录当前遍历到的位置
		Trie trie;
		ArrayDeque<StackValue> values = new ArrayDeque<StackValue>();

		public TrieIterator(final TrieNode begin,final Trie t) { // 构造方法
			stack.add(new StackValue(begin,new ArrayList<String>()));
			trie = t;
		}

		public boolean hasNext() { // 是否还有更多的元素可以遍历
			return (!values.isEmpty()) || (!stack.isEmpty());
		}

		public StackValue next() { // 向下遍历
			if(!values.isEmpty()){
				return values.pop();
			}
			
			while (!stack.isEmpty()) {
				final StackValue stackValue = stack.pop();
				final Set<String> ret = edges(stackValue.node);
				for (final String edge : ret) {
					//同步向前遍历
					final TrieNode state1 = trie.next(stackValue.node, edge);
					if (state1 == null) {
						continue;
					}
					final ArrayList<String> rhs = new ArrayList<String>(stackValue.rhs);
					rhs.add(edge);
					final StackValue s = new StackValue(state1,rhs);
					stack.add(s);
					if (state1.isTerminal()) {//可结束状态
						//String ruleName = state1.ruleName.ruleName;
						//return s;
						values.add(s);
					}
				}
				if(!values.isEmpty()){
					return values.pop();
				}
			}
			
			return null;
		}

		public void remove() { // 从集合中删除当前元素
			throw new UnsupportedOperationException(); // 不支持删除当前元素这个操作
		}
	}

	@Override
	public Iterator<StackValue> iterator() {
		return new TrieIterator(this.rootNode,this);
	}
}
