package wang.jinggo.ES.cnword.bigramSeg;

import junit.framework.TestCase;

import com.lietu.biSeg.PartOfSpeech;
import com.lietu.biSeg.Segmenter;
import com.lietu.biSeg.Tagger;
import com.lietu.biSeg.WordToken;

public class TestTagger extends TestCase{

	public static void main(String[] args) throws Exception {
		//dataTest();
		//testTagger();
		testTagger();
	}

	public static void testData() {
		Tagger tagger = Tagger.getInstance();
		System.out.println(tagger.getTransProb(PartOfSpeech.start,
				PartOfSpeech.a));
		System.out.println(tagger.getTransProb(PartOfSpeech.start,
				PartOfSpeech.n));
		// System.out.println(tagger.getTransProb(PartOfSpeech.start.ordinal(),PartOfSpeech.det.ordinal()));
	}
	
	public static void testTagger() throws Exception{
		String text = 
				"会上，市督察办党组书记贺南南高度肯定了白下区“12345”工作取得的进步和良好的为民服务意识。"; //TODO error
				//"巨星和苯磺隆为高效内吸药剂，一般施药后一周左右杂草开始枯黄死亡。";
				//"请问如果是加拿大公民第一次回国工作，上述情况又如何？";
		//"怎么没有放行";
				//"汽车手续入境办理";
				//"他!@#$%^&*()_jdks;就覅上呢交给你个案件发达，他曾在北京师范大学心理系读书";
				//"你有QQ吗,我的是222333";
				// "the luxury auto maker last year sold 1,214.787878 cars in the U.S.";
				//"选择分词器";

		Segmenter seg = new Segmenter();
		//List<WordTokenInf> words = seg.split(text);
		
		//Tagger tagger = Tagger.getInstance();
		//System.out.println(tagger.viterbi(words));
		WordToken[] tokens = seg.tag(text);
		System.out.println("输出标注结果：");
		for (WordToken w : tokens) {
			System.out.println(w + " / ");
		}
		System.out.println("");
	}
	
}
