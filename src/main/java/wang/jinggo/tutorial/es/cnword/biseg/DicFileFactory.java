package wang.jinggo.tutorial.es.cnword.biseg;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @author wangyj
 * @description
 * @create 2018-11-13 11:01
 **/
public class DicFileFactory implements DicFactory {

    public static final String dicDir = "./dic/";
    public static final String binDic = "coreDict.txt";

    @Override
    public TernarySearchTrie create() {
        TernarySearchTrie dic = new TernarySearchTrie();

        File dataFile = new File(dicDir + binDic);

        try {
            InputStream input = new FileInputStream(dataFile);
            BufferedReader read = new BufferedReader(new InputStreamReader(input, "GBK"));
            String line;
            try {
                while ( ((line = read.readLine()) != null)) {
                    //System.out.println(line);
                    if("".equals(line))
                        continue;

                    StringTokenizer st = new StringTokenizer(line,":");
                    String key = st.nextToken();
                    String pos = st.nextToken();
                    int freq;
                    try {
                        freq = Integer.parseInt(st.nextToken());
                    } catch (Exception e) {
                        freq = 1;
                    }
                    dic.addWord(key,pos,freq);
                    dic.totalFreq += freq;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dic;
    }
}
