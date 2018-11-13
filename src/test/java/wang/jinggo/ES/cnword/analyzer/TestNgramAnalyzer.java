package wang.jinggo.ES.cnword.analyzer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.lietu.bigramSeg.NgramAnalyzer;

public class TestNgramAnalyzer extends TestCase {
	/**
	 * @param args
	 */
	public static void main(String[] args)  throws IOException {
		// text to tokenize
		//final String text = "中日战争爆发";
		//final String text = "他很聪明,很活跃,才一岁两个月";
		//
			//"转载请以链接形式标明本文地址";
			//"有意见分歧";
		final String text = //"保护用户隐私";
			//"转载请以链接形式标明本文地址";
				//"value1中文处理";
				"1212中文处理yyyhh";
				//"从中学到知识";
			//"他很聪明,很活跃,才一岁两个月";
		//final String text = "大学生活动中心";
		
		double start = System.currentTimeMillis();
		NgramAnalyzer analyzer = new NgramAnalyzer();
		
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
		double end = System.currentTimeMillis();
		System.out.println(end - start);
		
		stream.end();
		stream.close();
		analyzer.close();
	}
	
	public static void testFile() throws IOException
	{
		String fileName = "";
		try 
		{
			FileReader filereadnew = new FileReader(fileName);
			BufferedReader read = new BufferedReader(filereadnew);
			NgramAnalyzer analyzer = new NgramAnalyzer();
			String line = null;
			
			while ((line = read.readLine()) != null) 
			{
				TokenStream stream = analyzer.tokenStream("field", new StringReader(
						line));	
				// get the TermAttribute from the TokenStream
				CharTermAttribute termAtt = stream.addAttribute(CharTermAttribute.class);
				TypeAttribute typeAtt = stream.addAttribute(TypeAttribute.class);

				stream.reset();	

				System.out.println(line);
				// print all tokens until stream is exhausted		              
				while (stream.incrementToken()) 
				{
					System.out.print(termAtt.toString() + typeAtt.type() + " ");
					
				}
				System.out.println("");	
				stream.end();
				stream.close();	
			}			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}	
	}
}
