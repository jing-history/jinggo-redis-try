package wang.jinggo.ES.cnword.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.lietu.bigramSeg.NgramAnalyzer;
import com.lietu.ds.CnAnalyzerProvider;

public class TestCnAnalyzerProvider {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		final String text = //"保护用户隐私";
			"转载请以链接形式标明本文地址";
		//Index i = null;
		CnAnalyzerProvider p = new CnAnalyzerProvider(null,  null, null, null);
		NgramAnalyzer analyzer = p.get();
		
		TokenStream stream = analyzer.tokenStream("field", new StringReader(
				text));//NgramAnalyzer继承Analyzer，tokenStream是Analyzer成员函数

		// get the TermAttribute from the TokenStream
		CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);
		OffsetAttribute offsetAtt = stream.addAttribute(OffsetAttribute.class);
		TypeAttribute typeAtt = stream.addAttribute(TypeAttribute.class);

		stream.reset();

		// print all tokens until stream is exhausted
		while (stream.incrementToken()) {
			//System.out.println(termAtt.toString()+" /"+offsetAtt.startOffset()+","+offsetAtt.endOffset());
			System.out.println(termAtt.toString()+" /"+offsetAtt.startOffset()+","+offsetAtt.endOffset()
			 + " "+typeAtt.type());
		}
		//double end = System.currentTimeMillis();
		//System.out.println(end - start);
		
		stream.end();
		stream.close();
	}

}
