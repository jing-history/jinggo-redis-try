package wang.jinggo.ES.cnword.bigramSeg;

import com.lietu.biSeg.BigramMap;

public class TestBigramMap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigramMap bm = new BigramMap(10,0);
		bm.put(19, 9);
		bm.put(18, 8);
		bm.put(17, 7);
		bm.put(16, 6);
		bm.put(15, 16);
		System.out.println(bm);
	}
}
