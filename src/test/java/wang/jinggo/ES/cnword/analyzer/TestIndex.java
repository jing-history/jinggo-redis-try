package wang.jinggo.ES.cnword.analyzer;

import java.io.IOException;
import java.util.Random;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.MockAnalyzer;
import org.apache.lucene.analysis.MockTokenizer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.LuceneTestCase;

import com.carrotsearch.randomizedtesting.RandomizedTest;

public class TestIndex extends RandomizedTest {

	public static void test1() throws IOException {
		Random r = LuceneTestCase.random();
		Analyzer analyzer = new MockAnalyzer(r,
				MockTokenizer.SIMPLE, true);
		Directory rd = new RAMDirectory();
		IndexWriter w = new IndexWriter(rd, new IndexWriterConfig(analyzer));
	}

}
