package wang.jinggo.ES.cnword.bigramSeg;

import java.io.File;
import java.util.ArrayList;

import com.lietu.biSeg.SuffixBigram;
import com.lietu.biSeg.WordEntry;
import com.lietu.biSeg.SuffixBigram.TSTNode;

public class TestBigramDic {

	public static void main(String[] args) {
		//testFile();
		//testFindWord();
		testBinary();
	}
	
	public static void testFile(){
		//delete file
		File dicFile = new File("./dic/" + SuffixBigram.binDic);
		boolean success = dicFile.delete();
		System.out.println(success);
		
		SuffixBigram dict = SuffixBigram.getInstance("./dic/");
		String sentence = "中国北京";

		int i = 4;
		ArrayList<WordEntry> wordMatch = new ArrayList<WordEntry>();
		dict.matchAll(sentence, i - 1, wordMatch);// 到词典中查询
		for (WordEntry w : wordMatch) {
			System.out.println(w);
		}
	}
	
	public static void testFindWord(){
		SuffixBigram dict = SuffixBigram.getInstance("./dic/");
		String word = "的";//"剂";//"北京";
		TSTNode node = dict.getNode(word);
		System.out.println(node.data.biEntry);
	}
	
	public static void testBinary(){
		SuffixBigram dict = SuffixBigram.getInstance("./dic/");
		String prev = "北京";
		String next = "的";
		int bigramFreq = dict.getBigramFreq(prev, next);
		System.out.println(bigramFreq);
	}
}
