package wang.jinggo.tutorial.es.cnword.template;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class WordType {
	public String type; // 类型
	public String questionNo; // 问题编号,一个词可以没有问题编号

	public WordType(String t) {
		type = t;
	}

	public WordType(String t, String q) {
		type = t;
		questionNo = q;
	}

	/*public WordType(Nonterminal ruleName) {
		type = ruleName.ruleName;
		questionNo = ruleName.qestionNo;
	}*/

	public String getRuleType() {
		if (questionNo == null)
			return type;
		return type + ":" + questionNo;
	}

	public String toString() {
		if (questionNo == null)
			return type;
		return type + ":" + questionNo;
	}
	
	public WordType(DataInputStream inStream) throws IOException{
		int length = inStream.readInt();
		if (length > 0) {
			byte[] bytebuff = new byte[length];
			inStream.read(bytebuff);
			type = new String(bytebuff, "UTF-8"); // 记录每一个词语
		}
		System.out.println("WordType type length:"+length);
		
		length = inStream.readInt();
		System.out.println("WordType questionNo length:"+length);
		if (length > 0) {
			byte[] bytebuff = new byte[length];
			inStream.read(bytebuff);
			questionNo = new String(bytebuff, "UTF-8"); // 记录每一个词语
		}
	}

	public void save(DataOutputStream outStream) throws IOException {
		Charset charset = Charset.forName("utf-8"); // 得到字符集
		CharBuffer cBuffer = CharBuffer.wrap(type);
		ByteBuffer bb = charset.encode(cBuffer);

		// 写入词的长度
		outStream.writeInt(bb.limit());
		// 写入词的内容
		for (int i = 0; i < bb.limit(); ++i)
			outStream.write(bb.get(i));

		if (questionNo == null) {
			outStream.writeInt(0);
			return;
		}
		cBuffer = CharBuffer.wrap(questionNo);
		bb = charset.encode(cBuffer);

		// 写入词的长度
		outStream.writeInt(bb.limit());
		// 写入词的内容
		for (int i = 0; i < bb.limit(); ++i)
			outStream.write(bb.get(i));
	}
	
	@Override
	public int hashCode(){
		return type.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		if(!(o instanceof WordType)) //判断传入对象的类型
			return false;
		WordType that = (WordType)o;
	
		return this.type.equals(that.type);
	}
}
