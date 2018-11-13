package wang.jinggo.tutorial.es.cnword.biseg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 存储从词编号到频率的映射，采用折半查找
 * 二元关系映射,用来存储某个词的二元关系
 * @author wangyj
 * @description
 * @create 2018-11-12 20:01
 **/
public class BigramMap {

    public int[] prevIds; // 前缀词id集合
    public int[] freqs; // 组合频率集合
    public int id;// 词本身的id

    public BigramMap(int id,int len) {
        this.id = id;
        prevIds = new int[len];
        freqs = new int[len];
    }

    /**
     * 插入时排序
     * @param prevId
     * @param freq
     */
    public void put(int prevId, int freq) { //插入时按前缀词id排序
        int index = indexOf(prevId);
        if (index == -1) { // 前缀不存在
            grow();
            for (index = prevIds.length - 1; index > 0 && prevIds[index - 1] > prevId; index--) {
                prevIds[index] = prevIds[index - 1];
                freqs[index] = freqs[index - 1];
            }//指定位置之后的元素后移一个位置
        }//插入指定位置的元素
        prevIds[index] = prevId;
        freqs[index] = freq;
    }

    public int get(int prevId) {
        int index = indexOf(prevId);
        if (index != -1)
            return freqs[index];
        return -1;
    }

    private void grow() {
        int newLength = prevIds.length + 1;
        prevIds = Arrays.copyOf(prevIds, newLength);
        freqs = Arrays.copyOf(freqs, newLength);
    }

    /**
     * 二分查找
     * @param prevId
     * @return
     */
    private int indexOf(int prevId) {
        int low = 0;
        int high = prevIds.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >>> 1);
            int midVal = prevIds[mid];

            if (midVal < prevId)
                low = mid + 1;
            else if (midVal > prevId)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -1;
    }

    public void add(int i ,int key, int val){
        prevIds[i] = key;
        freqs[i] = val;
    }

    public BigramMap(DataInputStream inStream) throws IOException {
        id = inStream.readInt(); /* 获取词的id */

        int len = inStream.readInt(); /* 获取文件中key和value的数量 */
        prevIds = new int[len];
        freqs = new int[len];

        for (int i = 0; i < len; i++) {
            prevIds[i] = inStream.readInt();
            freqs[i] = inStream.readInt();
        }
    }

    public void save(DataOutputStream outStream) throws IOException{
        outStream.writeInt(id);
		/* 写入key的数量 */
        outStream.writeInt(prevIds.length);

		/* 写入前缀词编号 */
        for (int i = 0; i < prevIds.length; i++) {
            outStream.writeInt(prevIds[i]);
            outStream.writeInt(freqs[i]);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("BigramMap@"); //NOI18N

        for (int i=0; i < prevIds.length; i++) {
            sb.append ("["); //NOI18N
            sb.append (prevIds[i]);
            sb.append (":"); //NOI18N
            sb.append (freqs[i]);
            sb.append ("]"); //NOI18N
        }
        if (prevIds.length == 0) {
            sb.append("empty"); // NOI18N
        }
        return sb.toString();
    }
}
