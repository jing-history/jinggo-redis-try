package wang.jinggo.ES.cnword.bigramSeg;

import java.util.HashMap;

public class TestDic {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		int N = 20000;
		int w1=5; //前一个词的编号
		int w2=8; //后一个词的编号
		
		int[][] biFreq = new int[N][N];
		int freq = biFreq[w1][w2];
		*/
		HashMap<String,HashMap<String,Integer>> bigrams = new HashMap<String,HashMap<String,Integer>>() ;
		HashMap<String,Integer> val = new HashMap<String,Integer>();
		val.put("北京", 10);
		val.put("上海", 100);
		bigrams.put("中国", val);
		
		System.out.println(bigrams.get("中国").get("上海"));
	}

}
