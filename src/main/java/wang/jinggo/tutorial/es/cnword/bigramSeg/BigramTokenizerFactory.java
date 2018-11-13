package wang.jinggo.tutorial.es.cnword.bigramSeg;

import java.util.Map;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

public class BigramTokenizerFactory extends TokenizerFactory{
	
	public BigramTokenizerFactory(Map<String, String> args) {
		super(args);
	}

	@Override
	public Tokenizer create(AttributeFactory factory) {
		String dicPath = "./dic/";
		BigramDictioanry dict = BigramDictioanry.getInstance(dicPath);
		return new BigramTokenizer(factory,dict);
	}

}
