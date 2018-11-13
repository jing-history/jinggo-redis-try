package wang.jinggo.ES.cnword.bigramSeg;

import java.io.File;

import com.lietu.biSeg.PrefixBigram;
import com.lietu.biSeg.PrefixBigram.TSTNode;

public class TestPrefixBigram {

	public static void main(String[] args) {
		PrefixBigram dict = PrefixBigram.getInstance("./dic/");
		String word = "的";//"剂";//"北京";
		System.out.println(dict.isActiveWord(word));
	}
	
	public static void testBigram(){
		PrefixBigram dict = PrefixBigram.getInstance("./dic/");
		String word = "的";//"剂";//"北京";
		TSTNode node = dict.getNode(word);
		System.out.println(node.data.biEntry);
		
		String prev = "北京";
		String next = "的";
		int bigramFreq = dict.getBigramFreq(prev, next);
		System.out.println(bigramFreq);
	}
	
	public static void testFile(){
		//delete file
		File dicFile = new File("./dic/" + PrefixBigram.binDic);
		boolean success = dicFile.delete();
		System.out.println(success);
		
	}

}
