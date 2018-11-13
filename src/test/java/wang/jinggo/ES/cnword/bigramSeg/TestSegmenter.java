package wang.jinggo.ES.cnword.bigramSeg;

import java.util.List;

import junit.framework.TestCase;

import com.lietu.biSeg.CnToken;
import com.lietu.biSeg.Segmenter;
import com.lietu.biSeg.WordTokenInf;

/**
 * @author luogang
 * @2013-8-5
 */
public class TestSegmenter extends TestCase {

	public static void main(String[] args) throws Exception {
		// testPath();
		testSeg();
		// testUnKnowSeg();
	}

	public static void testSeg() throws Exception {
		String sentence = // "市委常委、常务副市长沈健也亲临指导。";
		// "可提供如图正在下载的种子";
		// "有意见分歧";
		 //"中国成立了";
		//"大学生活动中心NBA";
		 "会上，市督察办党组书记贺南南高度肯定了白下区“12345”工作取得的进步和良好的为民服务意识。";
		//"巨星和苯磺隆为高效内吸药剂，一般施药后一周左右杂草开始枯黄死亡。";
				//"从中学到知识";
		Segmenter seg = new Segmenter();
		List<WordTokenInf> words = seg.split(sentence);
		for (WordTokenInf word : words) {
			System.out.print(word.termText + " ");
			System.out.print(' ');
		}
	}

	public static void testPath() throws Exception {
		Segmenter seg = new Segmenter();
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
