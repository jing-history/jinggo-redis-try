/*
 * Created on 2005-9-25
 *
 */
package wang.jinggo.tutorial.es.cnword.bigramSeg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

/**
 * 一个保护原子词的Filter
 * 
 * @author luogang
 */
public final class AtomFilter extends TokenFilter {
	private CharTermAttribute termAtt;
	private OffsetAttribute offsetAtt;
	private PositionIncrementAttribute posIncr;
	private char[] curTermBuffer;
	private int curPos;
	private int curTermLength;
	private int i = 0;

	private List<char[]> atomBuffer;

	public AtomFilter(TokenStream in) {
		super(in);
		this.termAtt = addAttribute(CharTermAttribute.class);
		this.offsetAtt = addAttribute(OffsetAttribute.class);
		this.posIncr = addAttribute(PositionIncrementAttribute.class);
	}

	public List<String> automSplit(String word) {
		AtomTernarySearchTrie atst = AtomTernarySearchTrie.getInstance();
		List<String> words = new ArrayList<String>();

		for (int i = 0; i < word.length();) {
			String w = atst.getMatch(word, i);
			if (!"".equals(w)) {
				words.add(w);
				i += w.length();
			} else {
				words.add(word.substring(i, i + 1));
				i++;
			}
		}

		return words;
	}

	public List<char[]> automSplit(char[] word, int len) {
		AtomTernarySearchTrie atst = AtomTernarySearchTrie.getInstance();
		ArrayList<char[]> words = new ArrayList<char[]>();

		for (int i = 0; i < len;) {
			char[] w = atst.getMatch(word, i, len);
			if (w != null) {
				words.add(w);
				i += w.length;
			} else {
				words.add(new char[] { word[i] });
				i++;
			}
		}

		if (words == null || words.size() == 1)
			return null;

		return words;
	}

	@Override
	public boolean incrementToken() throws IOException {
		// 把词再次分成原子词或单字
		if (atomBuffer != null && (curPos < atomBuffer.size())) {
			offsetAtt.setOffset(i, i + atomBuffer.get(curPos).length);
			termAtt.copyBuffer(atomBuffer.get(curPos), 0, atomBuffer.get(curPos).length);
			posIncr.setPositionIncrement(0);
			i += atomBuffer.get(curPos).length;
			curPos++;
			return true;
		}

		if (input.incrementToken()) {
			curTermBuffer = termAtt.buffer();
			curTermLength = termAtt.length();
			i = offsetAtt.startOffset();
			curPos = 0;
			atomBuffer = automSplit(curTermBuffer, curTermLength);
			return true;
		}
		return false;
	}
}
