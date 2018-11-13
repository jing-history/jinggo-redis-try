package wang.jinggo.tutorial.es.cnword.ds;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;
import wang.jinggo.tutorial.es.cnword.bigramSeg.BigramDictioanry;
import wang.jinggo.tutorial.es.cnword.bigramSeg.BigramTokenizer;

/**
 * @author wangyj
 * @description
 * @create 2018-11-13 11:10
 **/
public class BigramESTokenizerFactory extends AbstractTokenizerFactory {

    public BigramESTokenizerFactory(IndexSettings indexSettings, String name,
                                    Settings settings) {
        super(indexSettings, name, settings);
    }

    @Override
    public Tokenizer create() {
        String dicPath = "./dic/";
        BigramDictioanry dict = BigramDictioanry.getInstance(dicPath);
        return new BigramTokenizer(dict);
    }
}
