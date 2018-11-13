package wang.jinggo.tutorial.es.cnword.biseg;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-11-13 11:05
 **/
public class PathFinder {

    public CnToken startNode; // 开始词
    public CnToken endNode; // 结束词

    public List<WordTokenInf> bestPath() { // 根据最佳前驱节点找切分路径
        ArrayDeque<CnToken> seq = new ArrayDeque<CnToken>(); // 切分出来的词序列

        for (CnToken t = endNode.bestPrev; t != startNode; t = t.bestPrev) { // 从右向左找最佳前驱节点
            seq.addFirst(t);
        }

        List<WordTokenInf> words = new ArrayList<WordTokenInf>(); // 切分出来的词序列

        for (CnToken w : seq) {
            // System.out.println("word "+ w);
            //end = start + w.word.length();
            int start = w.start;
            int end = w.end;
            WordTokenInf word = new WordTokenInf(start, end, w.termText, w.posInf);
            words.add(word);
            start = end;
        }
        return words;
    }
}
