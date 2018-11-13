package wang.jinggo.ES.cnword.fst;

import com.lietu.fst.FSTSGraph;
import com.lietu.fst.SegScheme;

import junit.framework.TestCase;

public class TestFSTSeg extends TestCase {

	public static void main(String[] args) throws Exception {
		FSTSGraph fstSeg = new FSTSGraph();
		String sentence = // "in a long drop, they reach speeds of 987,878 miles an hour and more.";
		 "the luxury auto maker last year sold 1,214.787878 cars in the U.S.";
		//"Mr. Vinken is chairman of Elsevier N.V., the Dutch publishing group. ";
		SegScheme wordGraph = fstSeg.seg(sentence);
		System.out.println(wordGraph);
	}

}
