package wang.jinggo.tutorial.es.cnword.bigramSeg;

import java.io.IOException;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

/**
 * 一个处理字词混合的Filter
 * @author luogang
 */
public final class SingleFilter extends TokenFilter {
	private CharTermAttribute termAtt; //词属性
	private OffsetAttribute offsetAtt; //位置属性
	private PositionIncrementAttribute posIncr; //位置增量属性
	private char[] curTermBuffer; //要再次切分开的词缓存
	private int curPos; //在词中的位置
	private int curTermLength; //长度缓存
	private int i = 0; //单字所在总的偏移量

	public SingleFilter(TokenStream in) {
		super(in);
		this.termAtt = addAttribute(CharTermAttribute.class);
		this.offsetAtt = addAttribute(OffsetAttribute.class);
		this.posIncr = addAttribute(PositionIncrementAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		// 把词分成单个字
		if (curPos < curTermLength && curTermLength > 1) {
			offsetAtt.setOffset(i, i + 1);
			termAtt.copyBuffer(curTermBuffer, curPos , 1);
			posIncr.setPositionIncrement(0);
			curPos++;
			i++;
			return true;
		}
		if (input.incrementToken()) {
			curTermBuffer = termAtt.buffer();
			curTermLength = termAtt.length();
			curPos = 0;
			i = offsetAtt.startOffset();
			return true;
		}
		return false;
	}
}
