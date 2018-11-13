package wang.jinggo.tutorial.es.cnword.fst;

import java.util.Collection;
import java.util.HashSet;

/**
 * 有向边
 * @author wangyj
 * @description
 * @create 2018-11-12 20:54
 **/
public class Token {

    public String termText;
    public String baseForm; //原型
    public Collection<Byte> type; //词的各种词性   change from NodeType
    public int start;
    public int end;

    public int distance;

    public Token(int vertexFrom, int vertexTo, String word) {
        start = vertexFrom;
        end = vertexTo;
        termText = word;
        baseForm = word;
        distance = 1;
    }

    public Token(int vertexFrom, int vertexTo, String word,HashSet<Byte> t) {
        start = vertexFrom;
        end = vertexTo;
        termText = word;
        baseForm = word;
        type = t;
        distance = 1;
    }

    public Token(String baseForm, String term, int vertexFrom, int vertexTo,
                 HashSet<Byte> types) {
        start = vertexFrom;
        end = vertexTo;
        termText = term;
        type = types;
        distance = 1;
        this.baseForm = baseForm;
    }

    public Token(int vertexFrom, int vertexTo, int dis) {
        start = vertexFrom;
        end = vertexTo;
        distance = dis;
    }

    public Token(String term, int vertexFrom, int vertexTo,
                 Collection<Byte> type) {
        baseForm = term;
        termText = term;
        start = vertexFrom;
        end = vertexTo;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        //System.out.println("CnToken  比较相等 " + this +" : that: " + obj);

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (end != other.end)
            return false;
        if (start != other.start)
            return false;
        if (termText == null) {
            if (other.termText != null)
                return false;
        } else if (!termText.equals(other.termText))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }

    public String toString() {
        return "text:" + baseForm + " start:" + start + " end:" + end+" types:"+type;
    }

    public static String upperCase(String term){
        char chars[] = term.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }
}
