package wang.jinggo.tutorial.es.cnword.fst;

import wang.jinggo.tutorial.es.cnword.biseg.AdjList;
import wang.jinggo.tutorial.es.cnword.biseg.CnToken;

import java.util.BitSet;
import java.util.Collection;

/**
 * 根据自动机得到词图
 * @author wangyj
 * @description
 * @create 2018-11-12 20:12
 **/
public class FSTSGraph {

    public FST fst;

    public FSTSGraph() throws Exception {
        fst = // FSTFactory.createSimple();
                FSTFactory.createAll();
    }

    public FSTSGraph(FST f) {
        fst = f;
    }

    public SegScheme seg(String sentence) throws Exception {
        int senLen = sentence.length();
        SegScheme segGraph = new SegScheme(senLen);
        AdjList wordGraph = new AdjList(senLen + 1);// 存储所有被切分的可能的词
        segGraph.wordGraph = wordGraph;
        int i = 0;// 用来控制匹配的起始位置的变量

        while (i < senLen) {
            CnToken token = fst.matchLong(sentence, i);
            if (token != null) {
                // System.out.println("FST切分的结果" + tokens);
                wordGraph.addEdge(token);

                // wordGraph.addPoints(tokens);

                i = token.end;
            } else { // 单个字符作为一个Token
                int end = i + 1;
                i = end;
            }
        }
        return segGraph;
    }

    public AdjList seg(String sentence, AdjList wordGraph) throws Exception {
        int senLen = sentence.length();
        int i = 0;// 用来控制匹配的起始位置的变量

        while (i < senLen) {
            CnToken token = fst.matchLong(sentence, i);
            if (token != null) {
                // System.out.println("FST切分的结果" + tokens);
                wordGraph.addEdge(token);

                // wordGraph.addPoints(tokens);

                i = token.end;
            } else { // 单个字符作为一个Token
                int end = i + 1;
                i = end;
            }
        }
        return wordGraph;
    }

    public BitSet getSplitPoints(String sentence) {
        int senLen = sentence.length();
        BitSet splitPoints = new BitSet(senLen);// 存储所有可能的切分点
        int i = 0;// 用来控制匹配的起始位置的变量

        while (i < senLen) {
            CnToken token = fst.matchLong(sentence, i);
            if (token != null) {
                // System.out.println("FST切分的结果" + tokens);
                splitPoints.set(token.start);
                splitPoints.set(token.end);
                i = token.end;
            } else { // 单个字符作为一个Token
                int end = i + 1;

                splitPoints.set(i);
                splitPoints.set(end);

                i = end;
            }
        }
        return splitPoints;
    }

    // 找最小的结束位置
    static int minEnd(Collection<Token> tokens) {
        int min = Integer.MAX_VALUE;

        for (Token t : tokens) {
            if (t.end < min) {
                min = t.end;
            }
        }
        return min;
    }
}
