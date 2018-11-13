package wang.jinggo.tutorial.es.cnword.bigramSeg;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.AttributeFactory;

public final class BigramTokenizer extends Tokenizer {
	int[] prevNode;
	double[] prob;
	double lambda1 = 0.3;
	double lambda2 = 0.7;

	private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);// 词属性
	private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);

	private static final int IO_BUFFER_SIZE = 4096;
	private char[] ioBuffer = new char[IO_BUFFER_SIZE];

	private boolean done; // 是否执行过的标志
	private int upto = 0;

	// 得到词典对象
	public CircularWordResultBuffer result;
	BigramDictioanry dict;

	/** Creates a new ThaiTokenizer */
	public BigramTokenizer(BigramDictioanry d) {
		this(DEFAULT_TOKEN_ATTRIBUTE_FACTORY, d);
	}

	public BigramTokenizer(AttributeFactory factory, BigramDictioanry d) {
		super(factory);
		// this.offsetAtt = addAttribute(OffsetAttribute.class);
		// this.termAtt = addAttribute(CharTermAttribute.class);
		this.done = false;

		ThreadContext threadContext = ThreadContext.getInstance();
		result = threadContext.result;
		this.dict = d;
	}

	/* 从二元字典中查找每个词对应的前缀的频率,如果没有对应的前缀则频率返回0 */
	public int getBigramFreq(WordEntry prev, WordEntry next) {
		// System.out.println(prev.termText +"@"+next.termText);
		/* 找到的对象中查找是否有名为prev的前缀及其频率 */
		int frq = 0;

		if ((next.biEntry != null) && (prev.biEntry != null))
			frq = next.biEntry.get(prev.biEntry.id);

		if (frq < 0)
			return 0;
		else
			return frq;
	}

	// 计算出最大概率的数组
	public ArrayDeque<Integer> bestPath() { // 根据prevNode回溯求解最佳切分路径
		ArrayDeque<Integer> ret = new ArrayDeque<Integer>();
		for (int i = prevNode.length - 1; i > 0; i = prevNode[i]) { // 从右向左找前驱节点
			ret.addFirst(i);
		}
		return ret;// 存节点编号
	}

	public void bestPath(String sentence) {
		ArrayDeque<Integer> path = bestPath();
		// ArrayList<WordTokenInf> ret = new ArrayList<WordTokenInf>();

		// 输出结果
		int start = 0;
		for (Integer end : path) {
			// System.out.print(sentence.substring(start, end) + "/ ");
			WordToken tokenInf = new WordToken(start, end, sentence.substring(
					start, end), PartOfSpeech.unknow);
			result.write(tokenInf);// 把分词结果写到输出缓存result
			start = end;
		}
	}

	private static final WordEntry startWord = WordEntry.getStartWord(); // 开始词
	private static final double MIN_PROB = Double.NEGATIVE_INFINITY / 2;

	/* 执行分词操作 */
	// public ArrayList<WordToken> split(String sentence) throws Exception
	private void split(String sentence) throws Exception {
		WordEntry[] preCnToken;
		int len = sentence.length() + 1;// 字符串长度
		prevNode = new int[len];
		prob = new double[len];
		prob[0] = 0;// 节点0的初始概率是1,取log后是0
		preCnToken = new WordEntry[len]; // 最佳前驱词数组
		preCnToken[0] = startWord; // 节点0的最佳前驱词是开始词

		ArrayList<WordEntry> wordMatch = new ArrayList<WordEntry>();

		for (int i = 1; i < len; ++i) {
			// 计算节点i的最佳前驱节点
			double maxProb = MIN_PROB;
			int maxPrev = -1;
			WordEntry preToken = null;

			dict.matchAll(sentence, i - 1, wordMatch);// 到词典中查询

			for (WordEntry t1 : wordMatch) {
				int start = i - t1.word.length();
				// System.out.println("start is :"+start);
				// System.out.println("t1 is :"+t1);
				// System.out.println("i is :"+i);

				WordEntry t2 = preCnToken[start];

				int bigramFreq = getBigramFreq(t2, t1);// 从二元词典找二元频率

				double wordProb = lambda1 * t1.posInf.total / dict.n + lambda2
						* (bigramFreq / t2.posInf.total);// 平滑后的二元概率

				double nodeProb = prob[start] + (Math.log(wordProb));
				if (nodeProb > maxProb) {// 概率最大的算作最佳前趋
					maxPrev = start;
					maxProb = nodeProb;
					preToken = t1;
				}
			}
			prob[i] = maxProb; // 记录节点i的概率
			prevNode[i] = maxPrev; // 记录节点i的最佳前趋节点
			preCnToken[i] = preToken; // 节点i的最佳前驱词
		}
		// System.out.println(g.toString());
		bestPath(sentence);
	}

	public void resizeIOBuffer(int newSize) {
		if (ioBuffer.length < newSize) {
			// Not big enough; create a new array with slight
			// over allocation and preserve content
			final char[] newCharBuffer = new char[newSize];
			System.arraycopy(ioBuffer, 0, newCharBuffer, 0, ioBuffer.length);
			ioBuffer = newCharBuffer;
		}
	}

	@Override
	public boolean incrementToken() throws IOException {
		if (!done) {
			// clearAttributes();
			done = true;
			upto = 0;
			while (true) {
				final int length = input.read(ioBuffer, upto, ioBuffer.length
						- upto);// input读到缓存ioBuffer
				// System.out.println("sentence length :" + length );

				if (length == -1)
					break;
				upto += length;
				if (upto == ioBuffer.length)
					resizeIOBuffer(upto * 2);
			}
			// System.out.println("sentence upto :" + upto );
			String sentence = new String(ioBuffer, 0, upto);
			// System.out.println("sentence sentence :" + sentence );
			/* 执行切分 */
			result.clear();// 输出缓存清空
			try {
				split(sentence);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		WordToken token = result.read();
		if (token != null) {
			termAtt.setEmpty();
			clearAttributes();
			char[] word = token.termText.toCharArray();
			// System.out.println("Offset " + token.start + " "+token.end);
			offsetAtt.setOffset(token.start, token.end);
			termAtt.copyBuffer(word, 0, word.length);
			// 如果这个词是词库中的那么就打印出来
			// System.out.println("word " + String.valueOf(word));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void reset() throws IOException {
		super.reset();
		result.clear();
		this.done = false;
		this.upto = 0;
	}

	@Override
	public void end() throws IOException {
		super.end();
		// set final offset
		final int finalOffset = correctOffset(upto);
		offsetAtt.setOffset(finalOffset, finalOffset);
	}
}