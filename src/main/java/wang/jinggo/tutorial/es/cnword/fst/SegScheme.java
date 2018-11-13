package wang.jinggo.tutorial.es.cnword.fst;

import wang.jinggo.tutorial.es.cnword.biseg.AdjList;

import java.util.BitSet;

/**
 * 切分方案
 * @author wangyj
 * @description
 * @create 2018-11-12 21:07
 **/
public class SegScheme {

    public BitSet startPoints;// 可开始点
    public BitSet endPoints; // 可结束点

    public AdjList wordGraph;//词图

    public SegScheme(int senLen) {
        endPoints = new BitSet(senLen);// 存储所有可能的切分点
        startPoints = new BitSet(senLen);// 存储所有可能的切分点
        wordGraph = new AdjList(senLen+1);// 存储所有被切分的可能的词
    }

    @Override
    public String toString() {
        return "SegGraph [startPoints=" + startPoints + ", endPoints="
                + endPoints + ", wordGraph=" + wordGraph + "]";
    }

}
