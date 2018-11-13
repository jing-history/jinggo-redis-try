package wang.jinggo.tutorial.es.cnword.template;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 词项
 * 
 * @author luogang
 * 
 */
public class WordEntry {
	public String word; // 词
	public HashSet<WordType> types; // 词的各种词性

	public WordEntry(String w) {
		word = w;
	}

	public WordEntry(String w, String t) {
		word = w;
		types = new HashSet<WordType>();
		types.add(new WordType(t));
	}

	public WordEntry(String w, WordType t) {
		word = w;
		types = new HashSet<WordType>();
		types.add(t);
	}

	public WordEntry(String w, String t, String qNo) {
		word = w;
		types = new HashSet<WordType>();
		types.add(new WordType(t, qNo));
	}

	public WordEntry(String w, HashSet<WordType> value) {
		word = w;
		types = value;
	}

	public void addType(String type) {
		if (types == null)
			types = new HashSet<WordType>();
		types.add(new WordType(type));
	}

	public void addType(String type, String qNo) {
		if (types == null)
			types = new HashSet<WordType>();
		types.add(new WordType(type, qNo));
	}

	public void addType(WordType type) {
		if (types == null)
			types = new HashSet<WordType>();
		types.add(type);
	}

	public void addType(HashSet<WordType> value) {
		if (types == null)
			types = new HashSet<WordType>();
		types.addAll(value);
	}

	public String toString() {
		return word + ":" + types;
	}

	static Charset charset = Charset.forName("utf-8"); // 得到字符集

	public WordEntry(DataInputStream inStream, int length) throws IOException {
		byte[] bytebuff = new byte[length];
		inStream.read(bytebuff);
		word = new String(bytebuff, "UTF-8"); // 记录每一个词语
		int size = inStream.readInt();
		System.out.println("size:"+size);
		if(size==0)
			return;
		types = new HashSet<WordType>();
		for (int i = 0; i < size; ++i) {
			WordType e = new WordType(inStream);
			types.add(e);
		}
	}

	public void save(DataOutputStream outStream) throws IOException {
		CharBuffer cBuffer = CharBuffer.wrap(word);
		ByteBuffer bb = charset.encode(cBuffer);

		// 写入词的长度
		outStream.writeInt(bb.limit());
		// 写入词的内容
		for (int i = 0; i < bb.limit(); ++i)
			outStream.write(bb.get(i));

		if (types == null) {
			outStream.writeInt(0);
			return;
		}
		int size = types.size();
		System.out.println("size:"+size);
		outStream.writeInt(size);
		for (WordType w:types) {
			w.save(outStream);
		}
	}
}
