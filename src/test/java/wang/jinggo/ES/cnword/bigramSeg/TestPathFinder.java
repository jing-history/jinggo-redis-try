package wang.jinggo.ES.cnword.bigramSeg;

import java.util.List;

import com.lietu.biSeg.CnToken;
import com.lietu.biSeg.PathFinder;
import com.lietu.biSeg.WordTokenInf;

public class TestPathFinder {

	public static void main(String[] args) {
		PathFinder seg = new PathFinder();
		seg.startNode = new CnToken(-1, 0, "start");

		CnToken w1 = new CnToken(0, 1, "有");
		w1.bestPrev = seg.startNode;

		CnToken w2 = new CnToken(1, 3, "意见");
		w2.bestPrev = w1;

		CnToken w3 = new CnToken(3, 5, "分歧");
		w3.bestPrev = w2;

		seg.endNode = new CnToken(5, 6, "end");
		seg.endNode.bestPrev = w3;

		List<WordTokenInf> words = seg.bestPath();
		for (WordTokenInf word : words) {
			System.out.print(word.termText + " ");
			System.out.print(' ');
		}
	}

}
