package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Set;

/**
 * @author luogang
 * @2014-1-26 
 */
public class GraphMatcher {

	public static class StatePair {
		ArrayList<NodeType> path;
		int s1; // 词序列中的状态，也就是位置
		TrieNode s2; // 词性Trie树中的状态，也就是节点
		//StringBuilder text;

		public StatePair(ArrayList<NodeType> p, int state1, TrieNode state2) {
			path = p;
			this.s1 = state1;
			this.s2 = state2;
		}
	}

	public static final class MatchValue {
		public ArrayList<NodeType> posSeq; // 词性序列
		public String ruleName; // 规则名
		public int start;
		public int end;
		public StringBuilder termText;

		public MatchValue(String r, ArrayList<NodeType> p,int begin,int e) {
			this.posSeq = p;
			ruleName = r;
			start = begin;
			end = e;
		}

		public String toString() {
			return "词性序列:" + posSeq.toString() + " 规则名:" + ruleName +" 开始位置:"+start+" 结束位置:"+end;
		}
	}

	/**
	 * 取得词图和规则树都可以向前进的步骤
	 * 
	 * @param edges
	 *            词图上的边
	 * @param s
	 *            规则树上的类型
	 * @return 共同的有效输入
	 */
	public static ArrayList<NextInput> intersection(CnTokenLinkedList edges,
			Set<String> s) {
		ArrayList<NextInput> tmp = new ArrayList<NextInput>();
		for (CnToken x : edges) {
			//System.out.println("cn token "+x);
			for (NodeType t : x.types) {
				if (s.contains(t.type)) { // 规则树上的类型包含词所属的类型
					t.start = x.start;
					t.end = x.end;
					tmp.add(new NextInput(x.end, t,x.termText));
				}
			}
		}
		return tmp;
	}

	/**
	 * 图的指定位置开始和trie树匹配
	 * 
	 * @param g
	 *            人名特征词图
	 * @param offset
	 *            开始位置
	 * @return 匹配结果
	 */
	public static ArrayList<MatchValue> intersect(AdjList g, int offset,Trie ruleTrie) {
		ArrayList<MatchValue> match = new ArrayList<MatchValue>(); // 映射结果
		Deque<StatePair> stack = new ArrayDeque<StatePair>(); // 存储遍历状态的堆栈
		ArrayList<NodeType> path = new ArrayList<NodeType>(); // 类型序列
		
		stack.add(new StatePair(path, offset, ruleTrie.rootNode));
		while (!stack.isEmpty()) { // 堆栈内容不空
			StatePair stackValue = stack.pop(); // 弹出堆栈

			// 取出图中当前节点对应的边
			CnTokenLinkedList edges = g.edges(stackValue.s1);
			if (edges == null){
				//System.out.println("edges is null");
				continue;
			}

			// 取出树中当前节点对应的类型
			Set<String> types = stackValue.s2.getChildren().keySet();
			//System.out.println("types "+types);
			//System.out.println("trieNode "+stackValue.s2);
			ArrayList<NextInput> ret = intersection(edges, types); // 输入求交集
			if (ret == null)
				continue;
			for (NextInput edge : ret) { // 遍历每个有效的输入
				// 向下遍历树
				TrieNode state2 = stackValue.s2.next(edge.type.type);
				//System.out.println("edge.type "+edge.type);
				//System.out.println("stackValue.s2.getChildren() "+stackValue.s2.getChildren());
				//System.out.println("stackValue.s2.getChildren().get(edge.type) "+stackValue.s2.getChildren().get("询问农药p-0"));
				//System.out.println("state2 "+state2);
				// 向前遍历图上的边
				int end = edge.end;
				ArrayList<NodeType> p = new ArrayList<NodeType>(
						stackValue.path); // 复制一个新的数组
				p.add(edge.type); // 把当前的Type加入到路径
				
				stack.add(new StatePair(p, end, state2)); // 压入堆栈
				if (state2.isTerminal()) { // 是可以结束的节点
					match.add(new MatchValue(state2.getNodeValue(), p,offset,end));
				}
			}
		}

		return match;
	}
	
}
