package wang.jinggo.tutorial.es.cnword.bigramSeg;

import java.util.ArrayDeque;

public class CompleteTree {
	int[] keys;
	public int[] vals;// 频率

	public void buildArray(int[] keys, int[] values) {
		sortArrays(keys, values);

		int pos = 0; // 已经处理的位置
		this.keys = new int[keys.length]; // 完全二叉树数组
		this.vals = new int[keys.length];
		ArrayDeque<Span> queue = new ArrayDeque<Span>();
		queue.add(new Span(0, keys.length));
		while (!queue.isEmpty()) {
			Span current = queue.pop();
			int rootId = CompleteTree.getRoot(current.end - current.start)
					+ current.start;
			this.keys[pos] = keys[rootId];
			this.vals[pos] = values[rootId];
			pos++;
			if (rootId > current.start)
				queue.add(new Span(current.start, rootId));
			rootId++;
			if (rootId < current.end)
				queue.add(new Span(rootId, current.end));
		}

		// 输出排序结果
		/*
		 * for (int i : ret) { System.out.print(i + "\t"); }
		 */
	}

	// 对键数组排序，同时值数组也参考键数组调整位置
	public static void sortArrays(int[] keys, int[] values) {
		int i, j;
		int temp;
		//冒泡法排序
		for (i = 0; i < keys.length - 1; i++) {
			// 数组最后面已经?藕眯颍灾鸾ゼ跎傺反问?
			for (j = 0; j < keys.length - 1 - i; j++) {
				if (keys[j] > keys[j + 1]) {
					temp = keys[j];
					keys[j] = keys[j + 1];
					keys[j + 1] = temp;

					temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;
				}
			}
		}
	}

	public CompleteTree(int[] k, int[] v) {
		buildArray(k, v);
	}

	public int find(int data) { // 查找元素
		int index = 1; // 从根节点开始找，根节点编号是1
		while (index < keys.length) { // 该位置不是空
			if (data < keys[index]) { // 判断要向左找，还是向右找
				index = index << 1; // 左子树
			} else if (data == keys[index]) { // 找到??
				return vals[index];
			} else {
				index = index << 1 + 1; // 右子树
			}
		}
		return -1; // 没找到
	}

	/**
	 * 取得完全二叉树根节点
	 * 
	 * @param num
	 *            节点数
	 * @return 根节点编号
	 */
	public static int getRoot(int num) {
		int n = 1; // 计算满二叉树的节点数
		while (n <= num) {
			n = n << 1;
		}
		int m = n >> 1;
		// System.out.println("n:"+n);
		int bottom = num - m + 1;
		int leftMaxBottom = m >> 1;
		// System.out.println("leftMaxBottom:"+leftMaxBottom+" m :"+m);
		if (bottom > leftMaxBottom) { // 左边已经填满
			bottom = leftMaxBottom;
		}
		// System.out.println("bottom:"+bottom+" m>>1 :"+(m >> 1));

		int index = bottom; // 左边的底层节点数
		if (m > 1) { // 加上内部的节点数
			index += ((m >> 1) - 1);
		}
		return index;
	}

	static class Span {
		int start; // 开始区域
		int end; // 结束区域

		public Span(int s, int e) {
			start = s;
			end = e;
		}
	}
}
