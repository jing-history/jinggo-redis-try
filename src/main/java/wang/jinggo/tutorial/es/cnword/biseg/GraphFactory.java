package wang.jinggo.tutorial.es.cnword.biseg;

import java.util.ArrayList;

/**
 * 根据词典切分
 * @author wangyj
 * @description
 * @create 2018-11-13 11:03
 **/
public class GraphFactory {

    static SuffixBigram suffixDic = SuffixBigram.getInstance("./dic/");
    static final double frq =1.0;

    public static void seg(String sentence, AdjList g) {
        int sLen = sentence.length()+1;// 字符串长度
        ArrayList<WordEntry> wordMatch = new ArrayList<WordEntry>();

        // 生成切分词图
        for (int i = 1; i < sLen;++i) {
            boolean match = suffixDic.matchAll(sentence, i-1, wordMatch);// 到词典中查询

            if (match)// 已经匹配上
            {
                for (WordEntry word : wordMatch) {
                    // System.out.println(word);
                    int start = i - word.word.length();// 词的开始位置
                    double freq = word.posInf.total;
                    if(freq==0.0){
                        freq = 0.5;
                    }
                    //double logProb = Math.log(freq) - Math.log(dict.totalFreq);
                    g.addEdge(new CnToken(start, i, freq, word.word,word.posInf,word.biEntry));
                }
            } else {
                int start = i-1;
                //double logProb = Math.log(frq) - Math.log(dict.totalFreq);
                g.addEdge(new CnToken(start,i,frq, sentence.substring(start,i),new WordTypes(0),null));
            }
        }
    }
}
