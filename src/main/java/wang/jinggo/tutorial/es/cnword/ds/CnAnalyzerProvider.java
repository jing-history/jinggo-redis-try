package wang.jinggo.tutorial.es.cnword.ds;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import wang.jinggo.tutorial.es.cnword.bigramSeg.NgramAnalyzer;

import java.io.File;
import java.nio.file.Path;

/**
 * @author wangyj
 * @description
 * @create 2018-11-13 11:19
 **/
public class CnAnalyzerProvider extends AbstractIndexAnalyzerProvider<NgramAnalyzer> {

    private static final Logger logger = LoggerFactory.getLogger(CnAnalyzerProvider.class);

    private final NgramAnalyzer analyzer;

    @Inject
    public CnAnalyzerProvider(IndexSettings indexSettings, Environment env, @Assisted String name, @Assisted Settings settings){
        super(indexSettings, name, settings);
        Path pluginDir = env.pluginsFile();  //得到插件目录，词典文件放在插件目录的子目录下
        //System.out.println("pluginDir:"+pluginDir);
        //logger.info("pluginDir:"+pluginDir);  //D:\elasticsearch-1.0.0\plugins
        //String dicPath=new File(pluginDir,"dic").getPath();
        //analyzer = new NgramAnalyzer();

        String dicPath=new File(pluginDir.toFile(),"seg/dic").getPath();
        logger.info("plugin dic Dir:"+dicPath);
        analyzer = new NgramAnalyzer(dicPath+"/");
    }

    @Override
    public NgramAnalyzer get() {
        return analyzer;
    }
}
