package wang.jinggo.tutorial.es.cnword.biseg;

import wang.jinggo.tutorial.es.cnword.fst.FSTSGraph;

/**
 * 二元分词
 * @author wangyj
 * @description
 * @create 2018-11-12 19:46
 **/
public class Segmenter {

    //static TernarySearchTrie dict = TernarySearchTrie.getInstance(); // 得到词典
    static SuffixBigram suffixDic = SuffixBigram.getInstance("./dic/");

    public static final double minValue =  -1000000.0;

    public CnToken startNode; // 开始词
    public CnToken endNode; // 结束词

    public FSTSGraph fstSeg;


}
