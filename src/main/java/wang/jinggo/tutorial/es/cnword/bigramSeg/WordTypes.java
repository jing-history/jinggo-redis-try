package wang.jinggo.tutorial.es.cnword.bigramSeg;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class WordTypes {
	public byte[] keys;// 词性
	public int[] vals;// 频率
	public int total; // 词的总频率

	/** Creates a new instance of IntMap */
	public WordTypes(int len) {
		keys = new byte[len];
		vals = new int[len];
	}

	public WordTypes(DataInputStream inStream) throws IOException {
		byte typecount = inStream.readByte(); 
		total = inStream.readInt();
		keys = new byte[typecount];
		vals = new int[typecount]; // 给节点的data域赋值
		
		for (int i = 0; i < typecount; i++) {
			keys[i] = inStream.readByte();
			int val = inStream.readInt();
			vals[i] = val;
		}
	}

	public void save(DataOutputStream outStream) throws IOException {
		/* 写入key的数量 */
		outStream.writeByte(keys.length);
		outStream.writeInt(total);

		/* 写入前缀词编号 */
		for (int i = 0; i < keys.length; i++) {
			outStream.writeByte(keys[i]);
			outStream.writeInt(vals[i]);
		}
	}

	public void insert(int i ,byte key, int val) {
		keys[i] = key;
		vals[i] = val;
		total += val;
	}

	public void put(byte key, int val) {
		growArrays();

		keys[keys.length - 1] = key;
		vals[keys.length - 1] = val;
		total += val;
	}

	private void growArrays() {
		int newSize = keys.length + 1;
		// System.out.println("growArrays new size:"+newSize);
		byte[] newKeys = new byte[newSize];
		int[] newVals = new int[newSize];
		Arrays.fill(newKeys, (byte) 127); // So binarySearch works
		System.arraycopy(keys, 0, newKeys, 0, keys.length);
		System.arraycopy(vals, 0, newVals, 0, vals.length);
		keys = newKeys;
		vals = newVals;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("WordId:Freq"); // NOI18N

		for (int i = 0; i <= keys.length - 1; i++) {
			sb.append("[");
			sb.append(keys[i]);
			sb.append(":");
			sb.append(vals[i]);
			sb.append("]");
		}
		if (keys.length <= 0) {
			sb.append("empty");
		}
		return sb.toString();
	}
}
