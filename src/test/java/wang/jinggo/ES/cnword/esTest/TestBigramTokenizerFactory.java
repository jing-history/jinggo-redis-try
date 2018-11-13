package wang.jinggo.ES.cnword.esTest;

import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.analysis.TokenizerFactory;

import com.lietu.ds.BigramESTokenizerFactory;

public class TestBigramTokenizerFactory extends BaseTokenStreamTestCase {
	public void testSimple() throws Exception {
		final Index index = new Index("test", "_na_");
		final Settings.Builder indexSettings = Settings.builder().put(
				IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT);

		Reader reader = new StringReader("我购买了道具和服装。");
		TokenizerFactory factory = new BigramESTokenizerFactory(null, null, null);
		Tokenizer tokenizer = factory.create();
		tokenizer.setReader(reader);
		assertTokenStreamContents(tokenizer, new String[] { "我", "购买", "了",
				"道具", "和", "服装", "。" });
	}
}
