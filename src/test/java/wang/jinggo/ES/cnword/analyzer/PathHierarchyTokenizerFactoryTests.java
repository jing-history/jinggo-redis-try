package wang.jinggo.ES.cnword.analyzer;
import org.apache.lucene.analysis.BaseTokenStreamTestCase;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.test.ESTokenStreamTestCase;
import org.elasticsearch.test.IndexSettingsModule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.lietu.ds.BigramESTokenizerFactory;

import java.io.IOException;
import java.io.StringReader;

//ESTokenStreamTestCase

public class PathHierarchyTokenizerFactoryTests extends BaseTokenStreamTestCase {

	  @Test
    public void testDefaults() throws IOException {
        final Index index = new Index("test", "_na_");
        final Settings.Builder indexSettings = Settings.builder().put(IndexMetaData.SETTING_VERSION_CREATED, Version.CURRENT);
        //final Settings indexSettings = ESTokenStreamTestCase.newAnalysisSettingsBuilder().build();
        
        //Tokenizer tokenizer = new BigramTokenizerFactory(IndexSettingsModule.newIndexSettings(index, null)
        //        "path-hierarchy-tokenizer", Settings.EMPTY).create();
        
        //tokenizer.setReader(new StringReader("/one/two/three"));
        //assertTokenStreamContents(tokenizer, new String[] {"/one", "/one/two", "/one/two/three"});
        
    }

    public void testReverse() throws IOException {
        /*final Index index = new Index("test", "_na_");
        final Settings indexSettings = newAnalysisSettingsBuilder().build();
        Settings settings = newAnalysisSettingsBuilder().put("reverse", true).build();
        Tokenizer tokenizer = new PathHierarchyTokenizerFactory(IndexSettingsModule.newIndexSettings(index, indexSettings), null,
                "path-hierarchy-tokenizer", settings).create();
        tokenizer.setReader(new StringReader("/one/two/three"));
        assertTokenStreamContents(tokenizer, new String[] {"/one/two/three", "one/two/three", "two/three", "three"});
        */
    }

}