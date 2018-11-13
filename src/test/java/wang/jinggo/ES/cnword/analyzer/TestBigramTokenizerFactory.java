package wang.jinggo.ES.cnword.analyzer;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;

import com.lietu.bigramSeg.BigramTokenizerFactory;

public class TestBigramTokenizerFactory extends BaseTokenStreamTestCase{
	  public void testSimple() throws Exception {
	    Reader reader = new StringReader("我购买了道具和服装。");
	    TokenizerFactory factory = new BigramTokenizerFactory(new HashMap<String,String>());
	    Tokenizer tokenizer = factory.create(newAttributeFactory());
	    tokenizer.setReader(reader);
	    assertTokenStreamContents(tokenizer, 
	       new String[] { "我", "购买", "了", "道具", "和", "服装", "。" });
	}
}
