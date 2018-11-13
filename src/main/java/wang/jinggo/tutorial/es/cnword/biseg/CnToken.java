package wang.jinggo.tutorial.es.cnword.biseg;

import java.util.Collection;

/**
 * 有向边
 * @author wangyj
 * @description
 * @create 2018-11-12 20:11
 **/
public class CnToken {

    public String termText;
    public int start;
    public int end;
    public double freq; // 词本身的概率
    public double nodeProb;// 累积概率
    public WordTypes posInf;
    public CnToken bestPrev; // 最佳前驱词
    public BigramMap biEntry;

    public CnToken(int vertexFrom, int vertexTo, String word) {
        start = vertexFrom;
        end = vertexTo;
        termText = word;
    }

    public CnToken(int vertexFrom, int vertexTo,double frq, String word, WordTypes posInf, BigramMap biEntry) {
        start = vertexFrom;
        end = vertexTo;
        termText = word;
        freq = frq;
        this.posInf = posInf;
        this.biEntry = biEntry;
    }

    public CnToken(String term, int vertexFrom, int vertexTo,
                   Collection<Byte> type) {
        termText = term;
        start = vertexFrom;
        end = vertexTo;

        posInf = new WordTypes(1);

        posInf.keys[0] = type.iterator().next();
        posInf.vals[0] = 1;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder("text:" + termText + " start:" + start + " end:" + end
                + " cost:" + freq);
        //if (this.features != null) {
        //	buffer.append(", features=").append(this.features.toString());
        //}
        return buffer.toString();
    }
}
