package wang.jinggo.ES.cnword.bigramSeg;

import com.lietu.biSeg.AdjList;
import com.lietu.biSeg.CnToken;
import com.lietu.biSeg.Segmenter;

/**
 * @author luogang
 * @2013-8-6
 */
public class TestAdjList {

	public static void main(String[] args) throws Exception {
		Segmenter seg = new Segmenter();
		String sentence = "中国成立了";
		AdjList g = seg.getSegGraph(sentence);

		System.out.println(g.toString());

		for (CnToken currentWord : g) {
			System.out.println(currentWord);
		}
	}

}
