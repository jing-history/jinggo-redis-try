package wang.jinggo.tutorial.es.cnword.bigramSeg;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.util.AttributeFactory;

public class NgramAnalyzer extends Analyzer {
	BigramDictioanry dict;
	Tagger tagger;

	public NgramAnalyzer(String p) {
		init(p);
	}

	public NgramAnalyzer() {
		String path = "./dic/";
		init(path);
	}
	
	public void init(String dicPath){
		dict = BigramDictioanry.getInstance(dicPath);
		tagger = Tagger.getInstance(dicPath);
	}

	@Override
	protected TokenStreamComponents createComponents(String arg0) {
		//BigramDictioanry dict = BigramDictioanry.getInstance(path);
		//Tagger tagger = Tagger.getInstance(path);
		//Tokenizer source = new NgramTokenizer(
		//		AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY, reader, dict);
		
		Tokenizer source = new NgramTagnizer(
				AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY,  dict,tagger);
		
		// result = new AtomFilter(result);
		// TokenStream result = new SingleFilter(source);
		// return result;
		// return new TokenStreamComponents(source,result);

		//StopFilter.makeStopSet(Version.LUCENE_43, stopWords);
		return new TokenStreamComponents(source);
	}
}