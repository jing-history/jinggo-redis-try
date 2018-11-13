package wang.jinggo.tutorial.es.cnword.template;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 找图中的最短路径
 * @author luogang
 *
 */
public class PathFinder {
	QueryTemplate grammar;
	CnToken[] bestPrev; // 最佳前驱词
	String question;

	public PathFinder(QueryTemplate g) {
		grammar = g;
	}

	public Deque<CnToken> getPath(String q) {
		question=q;
		bestPrev = new CnToken[question.length()+1];
		// String question = //"化肥的说明书是什么";
		// "农药的说明书有没有农药";
		AdjList g = grammar.getLattice(question);
		System.out.println("图 " + g);

		IndexMinPQ<Integer> indexMinPQ = new IndexMinPQ<Integer>(
				question.length());

		CnTokenLinkedList ll = g.edges(0);
		int distance = 1;
		for (CnToken t : ll) {
			if (t.end == question.length()) {
				bestPrev[t.end] = t;
				return bestPath();//t.termText;
			}
			indexMinPQ.insert(t.end, distance);
			bestPrev[t.end] = t;
		}

		while (distance >= 0 && distance < 3) {
			if(indexMinPQ.isEmpty()){
				//System.out.println("indexMinPQ  空");
				return null;
			}
			int start = indexMinPQ.delMin();
			distance = indexMinPQ.currentKey;
			//else
			//	System.out.println("开始位置 " + start + " 距离 空");
			//System.out.println("开始位置 " + start + " 距离:" + distance);
			ll = g.edges(start);
			for (CnToken t : ll) {
				if (t.end == question.length()) {
					bestPrev[t.end] = t;
					System.out.println("开始位置 " + start + " 距离:" + distance);
					return bestPath();
				}
				indexMinPQ.insert(t.end, distance + 1);
				bestPrev[t.end] = t;
			}
		}
		return null;
	}

	public Deque<CnToken> bestPath() {
		Deque<CnToken> path = new ArrayDeque<CnToken>(); // 最佳节点序列

		// 从后向前回朔最佳前驱节点
		for (int i = question.length(); i > 0;) {
			// System.out.println("i"+i);
			CnToken w = bestPrev[i];
			path.push(w);
			i = w.start;
		}

		return path;
	}

}
