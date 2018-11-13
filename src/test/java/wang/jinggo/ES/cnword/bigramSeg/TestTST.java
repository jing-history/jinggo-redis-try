package wang.jinggo.ES.cnword.bigramSeg;

import com.lietu.biSeg.TernarySearchTrie;
import com.lietu.biSeg.WordEntry;

public class TestTST {

	public static void main(String[] args) {
		TernarySearchTrie dic = TernarySearchTrie.getInstance();

		String sentence = "大学生活动中心";
		int offset = 0;
		WordEntry ret = dic.matchLong(sentence, offset);
		System.out.println(sentence + " match:" + ret);
		// System.out.println(dic.rootNode.getPath());
	}

}
