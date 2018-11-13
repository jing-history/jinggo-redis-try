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
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

/**
 * 词典树
 * @author luogang
 *
 */
public class TernarySearchTrie implements Iterable<String>{

	public final static class TSTNode {
		public WordEntry data;

		protected TSTNode left;
		protected TSTNode mid;
		protected TSTNode right;

		public char spliter;

		public TSTNode(char key) {
			this.spliter = key;
		}

		public TSTNode() {
		}

		public String toString() {
			return "data: " + data + "   spliter:" + spliter;
		}

		public void addWord(String word, String type) {
			if(data==null){
				data = new WordEntry(word,type);
			}
			else{
				data.addType(type);
			}
		}

		public void addWord(String word, String type, String qNo) {
			if(data==null){
				data = new WordEntry(word,type,qNo);
			}
			else{
				data.addType(type,qNo);
			}
		}
		
		public void addWord(String word, WordType e) {
			if(data==null){
				data = new WordEntry(word,e);
			}
			else{
				data.addType(e);
			}
		}

		public void addWord(String word, HashSet<WordType> value) {
			if(data==null){
				data = new WordEntry(word,value);
			}
			else{
				data.addType(value);
			}
		}

		public boolean isTerminal() {
			return (data!=null);
		}
	}

	public TSTNode rootNode;

	public TernarySearchTrie() {
	}
	
	public TernarySearchTrie(File file) throws IOException {
		Charset charset = Charset.forName("utf-8"); // 得到字符集
		InputStream file_input = new FileInputStream(file);

		// 读取二进制文件
		BufferedInputStream buffer = new BufferedInputStream(file_input);
		DataInputStream data_in = new DataInputStream(buffer);

		// 获取节点数量
		int nodeCount = data_in.readInt();
		// System.out.print("nodeId:" + nodeId);

		TSTNode[] nodeList = new TSTNode[nodeCount + 1];// nodeId从1开始
		// 要预先创建出来所有的节点，因为当前节点要指向后续节点
		for (int i = 0; i < nodeList.length; i++) {
			nodeList[i] = new TSTNode();
		}

		for (int index = 1;index <= nodeCount;index++) {
			int currNodeIndex = data_in.readInt(); // 获得当前节点编号
			int leftNodeIndex = data_in.readInt(); // 获得当前节点左子节点编号
			int middleNodeIndex = data_in.readInt(); // 获得当前节点中子节点编号
			int rightNodeIndex = data_in.readInt(); // 获得当前节点右子节点编号
			
			TSTNode currNode = nodeList[currNodeIndex];
			// 获取splitchar值
			int length = data_in.readInt();
			byte[] bytebuff = new byte[length];
			data_in.read(bytebuff);
			currNode.spliter = charset.decode(
					ByteBuffer.wrap(bytebuff)).charAt(0);
			
			length = data_in.readInt();
			if(length>0){
				// 创建TSTNode节点
				currNode.data = new WordEntry(data_in,length);
			}
			// 生成树节点之间的对应关系，左、中、右子树
			if (leftNodeIndex > 0) {
				currNode.left = nodeList[leftNodeIndex];// 建立引用关系，下次循环进行内容填充
			}

			if (middleNodeIndex > 0) {
				currNode.mid = nodeList[middleNodeIndex];
			}

			if (rightNodeIndex > 0) {
				currNode.right = nodeList[rightNodeIndex];
			}
		}

		data_in.close();
		buffer.close();
		file_input.close();

		rootNode = nodeList[1];
	}

	public void addWords(HashMap<String,HashSet<WordType>> words) {
		for(Entry<String, HashSet<WordType>> e:words.entrySet()){
			TSTNode currentNode = creatTSTNode(e.getKey());
			currentNode.addWord(e.getKey(),e.getValue());
		}
	}

	public void addWord(String word, String type) {
		TSTNode currentNode = creatTSTNode(word);
		currentNode.addWord(word,type);
	}

	public void addWord(String word, String type,String qNo) {
		//System.out.println("add Word " + word + " 问题编号: "+qNo );
		TSTNode currentNode = creatTSTNode(word);
		//currentNode.addType(type);
		//currentNode.questionNo = qNo; //问题编号
		currentNode.addWord(word,type,qNo);
	}

	// 创建一个结点
	public TSTNode creatTSTNode(String key) throws NullPointerException,
			IllegalArgumentException {
		if (key == null) {
			throw new NullPointerException("空指针异常");
		}
		int charIndex = 0;
		if (rootNode == null) {
			rootNode = new TSTNode(key.charAt(0));
		}
		TSTNode currentNode = rootNode;
		while (true) {
			int compa = (key.charAt(charIndex) - currentNode.spliter);
			if (compa == 0) {
				charIndex++;
				if (charIndex == key.length()) {
					return currentNode;
				}
				if (currentNode.mid == null) {
					currentNode.mid = new TSTNode(key.charAt(charIndex));
				}
				currentNode = currentNode.mid;
			} else if (compa < 0) {
				if (currentNode.left == null) {
					currentNode.left = new TSTNode(key.charAt(charIndex));
				}
				currentNode = currentNode.left;
			} else {
				if (currentNode.right == null) {
					currentNode.right = new TSTNode(key.charAt(charIndex));
				}
				currentNode = currentNode.right;
			}
		}
	}

	public static final class MatchRet {
		public WordEntry wordEntry; //词类型
		public int end;  //结束位置
		
		public String toString(){
			return wordEntry+" end:"+end;
		}
	}

	public void matchWord(String key, int offset, MatchRet ret) {
		ret.wordEntry = null;
		if (key == null || rootNode == null || "".equals(key)) {
			return;
		}
		TSTNode currentNode = rootNode;
		int charIndex = offset;
		while (true) {
			if (currentNode == null) {
				return;
			}
			int charComp = key.charAt(charIndex) - currentNode.spliter;

			if (charComp == 0) {
				charIndex++;

				if (currentNode.data != null) {
					ret.wordEntry = currentNode.data; // 候选最长匹配词
					ret.end = charIndex;
				}
				if (charIndex == key.length()) {
					return; // 已经匹配完
				}
				currentNode = currentNode.mid;
			} else if (charComp < 0) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
	}

	public int nodeNums(){
		TSTNode currNode = rootNode;
		if (currNode == null)
			return 0;
		int count = 0;
		//System.out.println("count ");
		// 用于存放节点数据的队列
		Deque<TSTNode> queueNode = new ArrayDeque<TSTNode>();
		queueNode.addFirst(currNode);

		// 广度优先遍历所有树节点，将其加入至数组中
		while (!queueNode.isEmpty()) {
			count++;
			// 取出队列第一个节点
			currNode = queueNode.pollFirst();

			// 处理左子节点
			if (currNode.left != null) {
				queueNode.addLast(currNode.left);
			}

			// 处理中间子节点
			if (currNode.mid != null) {
				queueNode.addLast(currNode.mid);
			}

			// 处理右子节点
			if (currNode.right != null) {
				queueNode.addLast(currNode.right);
			}
		}
		
		return count;
	}

	//生成词典文件
	public void compileDic(File file) throws IOException {
		String charSet = "UTF-8";
		FileOutputStream outFile = new FileOutputStream(file);
		BufferedOutputStream buffer = new BufferedOutputStream(outFile);
		DataOutputStream dataOut = new DataOutputStream(buffer);
		TSTNode currNode = rootNode;
		if (currNode == null){
			outFile.close();
			return;
		}

		int currNodeNo = 1; // 当前节点编号
		int maxNodeNo = currNodeNo;
		
		// 用于存放节点数据的队列
		Deque<TSTNode> queueNode = new ArrayDeque<TSTNode>();
		queueNode.addFirst(currNode);

		// 用于存放节点编号的队列
		Deque<Integer> queueNodeIndex = new ArrayDeque<Integer>();
		queueNodeIndex.addFirst(currNodeNo);

		int nodeCount=nodeNums();
		dataOut.writeInt(nodeCount); //Trie树节点总数

		// 广度优先遍历所有树节点，将其加入至数组中
		while (!queueNode.isEmpty()) {
			// 取出队列第一个节点
			currNode = queueNode.pollFirst();
			currNodeNo = queueNodeIndex.pollFirst();

			// 写入本节点的编号信息
			dataOut.writeInt(currNodeNo);

			// 处理左子节点
			int leftNodeNo = 0; // 当前节点的左孩子节点编号
			if (currNode.left != null) {
				maxNodeNo++;
				leftNodeNo = maxNodeNo;
				queueNode.addLast(currNode.left);
				queueNodeIndex.addLast(leftNodeNo);
			}

			// 写入左孩子节点的编号信息
			dataOut.writeInt(leftNodeNo);

			// 处理中间子节点
			int middleNodeNo = 0; // 当前节点的中间孩子节点编号
			if (currNode.mid != null) {
				maxNodeNo++;
				middleNodeNo = maxNodeNo;
				queueNode.addLast(currNode.mid);
				queueNodeIndex.addLast(middleNodeNo);
			}

			// 写入中孩子节点的编号信息
			dataOut.writeInt(middleNodeNo);

			// 处理右子节点
			int rightNodeNo = 0; // 当前节点的右孩子节点编号
			if (currNode.right != null) {
				maxNodeNo++;
				rightNodeNo = maxNodeNo;
				queueNode.addLast(currNode.right);
				queueNodeIndex.addLast(rightNodeNo);
			}

			// 写入右孩子节点的编号信息
			dataOut.writeInt(rightNodeNo);

			byte[] splitCharByte = String.valueOf(currNode.spliter).getBytes(charSet);

			// 记录字节数组的长度
			dataOut.writeInt(splitCharByte.length);

			// 写入splitChar字节数组
			dataOut.write(splitCharByte);
			
			if (currNode.data != null) {// 是结束节点,data域不为空
				//保存词性
				currNode.data.save(dataOut);
			} else { // 不是结束节点,data域为空
				dataOut.writeInt(0); // 写入字符串的长度
			}
		}
		//System.out.println("maxNodeNo:"+maxNodeNo);
		dataOut.close();
		outFile.close();	
	}

	private Set<Character> edges(TSTNode currNode) {
		if(currNode==null||currNode.mid==null)
			return null;
		HashSet<Character> ret = new HashSet<Character>();
		Deque<TSTNode> queueNode = new ArrayDeque<TSTNode>();
		queueNode.addFirst(currNode.mid);
		
		// 广度优先遍历所有树节点，将其加入至数组中
		while (!queueNode.isEmpty()) {
			// 取出队列第一个节点
			currNode = queueNode.pollFirst();
			ret.add(currNode.spliter);

			// 处理左子节点
			if (currNode.left != null) {
				queueNode.addLast(currNode.left);
			}

			// 处理中间子节点
			if (currNode.mid != null) {
				queueNode.addLast(currNode.mid);
			}

			// 处理右子节点
			if (currNode.right != null) {
				queueNode.addLast(currNode.right);
			}
		}
		
		return ret;
	}

	public TSTNode next(TSTNode stackValue, char edge) {
		TSTNode currentNode = stackValue.mid;
		while (true) {
			if (currentNode == null) {
				return null;
			}
			int charComp = edge - currentNode.spliter;

			if (charComp == 0) {
				return currentNode;
			} else if (charComp < 0) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
	}
	
	private final class TrieIterator implements Iterator<String> { // 用于迭代的类
		Stack<StackValue> stack = new Stack<StackValue>(); // 记录当前遍历到的位置
		TernarySearchTrie trie;
		ArrayDeque<String> values = new ArrayDeque<String>();

		public TrieIterator(final TSTNode begin,final TernarySearchTrie t) { // 构造方法
			stack.add(new StackValue(begin));
			trie = t;
		}

		public boolean hasNext() { // 是否还有更多的元素可以遍历
			return (!values.isEmpty()) || (!stack.isEmpty());
		}

		public String next() { // 向下遍历
			if(!values.isEmpty()){
				return values.pop();
			}
			
			while (!stack.isEmpty()) {
				final StackValue stackValue = stack.pop();
				final Set<Character> ret = edges(stackValue.node);
				if(ret==null)
					continue;
				for (char edge : ret) {
					//同步向前遍历
					final TSTNode state1 = trie.next(stackValue.node, edge);
					if (state1 == null) {
						continue;
					}
					final StringBuilder rhs = new StringBuilder(stackValue.rhs);
					rhs.append(edge);
					final StackValue s = new StackValue(state1,rhs);
					stack.add(s);
					if (state1.isTerminal()) {//可结束状态
						values.add(rhs.toString());
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

	public final static class StackValue {
		public TSTNode node;
		public StringBuilder rhs;

		public StackValue(TSTNode n) {
			node = n;
			rhs = new StringBuilder();
			rhs.append(n.spliter);
		}

		public StackValue(TSTNode n, StringBuilder r) {
			node = n;
			rhs = r;
		}

		public String toString() {
			return node.toString() + " rhs:" + rhs;
		}
	}

	@Override
	public Iterator<String> iterator() {
		return new TrieIterator(this.rootNode,this);
	}

	public boolean matchAll(String sentence, int offset,
			ArrayList<WordEntry> wordMatch) {
		wordMatch.clear(); // 清空返回数组中的词
		if (sentence == null || rootNode == null || "".equals(sentence)) {
			return false;
		}
		//System.out.println("offset "+offset) ;
		TSTNode currentNode = rootNode;
		int charIndex = offset;
		while (true) {
			if (currentNode == null) {
				if (wordMatch.size() > 0) {
					return true;
				}
				return false;
			}
			int charComp = sentence.charAt(charIndex) - currentNode.spliter;
			if (charComp == 0) {
				if (currentNode.data != null) {
					// System.out.println(currentNode.data) ;
					wordMatch.add(currentNode.data);
				}
				//System.out.println("charIndex "+charIndex) ;
				charIndex++; // 继续往前找
				if (charIndex >= sentence.length()) {
					if (wordMatch.size() > 0) {
						return true;
					}
					return false;
				}
				currentNode = currentNode.mid;
			} else if (charComp < 0) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}
	}
}
