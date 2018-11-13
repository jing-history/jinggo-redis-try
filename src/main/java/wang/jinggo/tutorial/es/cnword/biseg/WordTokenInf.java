package wang.jinggo.tutorial.es.cnword.biseg;

/**
 * 带位置的词信息类
 * @author wangyj
 * @description
 * @create 2018-11-13 11:05
 **/
public class WordTokenInf {

    public String termText;
    public WordTypes data;
    public int start;
    public int end;
    public long cost = Constant.SINGLE_WORD_SMOOTH_VALUE;	// 默认的单子平滑系数是5

    public WordTokenInf(int vertexFrom, int vertexTo, String word, WordTypes d) {
        start = vertexFrom;
        end = vertexTo;
        termText = word;
        data = d;
        if (d != null) {
            cost = d.total;
        }

        //如果没有词性，则增加一个未知类型
        if(data==null || data.keys.length==0){
            data = new WordTypes(1);
            data.insert(0, PartOfSpeech.unknow, 1);
        }
    }

    public String toString() {
        return "text:" + termText + " start:" + start + " end:" + end + "--------data:" + data;
    }
}
