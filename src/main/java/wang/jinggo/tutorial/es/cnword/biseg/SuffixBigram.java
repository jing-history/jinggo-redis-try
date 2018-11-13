package wang.jinggo.tutorial.es.cnword.biseg;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 后缀二元词典
 * @author wangyj
 * @description
 * @create 2018-11-12 19:47
 **/
public class SuffixBigram {

    private static SuffixBigram dic = null;

    /**
     *
     * @return the singleton of basic dictionary
     * @throws FileNotFoundException
     */
    public static SuffixBigram getInstance(String dicDir) {
        if (dic == null) {
            try {
                dic = new SuffixBigram(dicDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dic;
    }

    public final class TSTNode {
        public WordEntry data = null;

        protected TSTNode loNode;
        protected TSTNode eqNode;
        protected TSTNode hiNode;

        public char splitChar;

        public TSTNode(char key) {
            this.splitChar = key;
        }

        public TSTNode() {
        }

        public String toString() {
            return "data  是" + data + "   spliter是" + splitChar;
        }
    }

    public TSTNode rootNode;
    public double totalFreq = 0; // 统计词典中总词频
    public int id = 1; // 存储每一个词的id
    public int nodeId = 1; /* 节点编号 */

    public void loadBaseDictionay(String path) throws Exception {
        InputStream file = new FileInputStream(new File(path));
        BufferedReader read = new BufferedReader(new InputStreamReader(file,
                "GBK"));

        String line = null;
        String pos;
        while ((line = read.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, ":");
            String key = st.nextToken(); // 单词文本
            pos = st.nextToken();
            // System.out.println("词:" + key + "词性:" + kk);
            byte code = PartOfSpeech.values.get(pos); // 词性编码
            int frq = Integer.parseInt(st.nextToken()); // 单词频率

            if (rootNode == null) {
                rootNode = new TSTNode(key.charAt(key.length() - 1));
            }

            TSTNode currentNode = getOrCreateNode(key);

			/* 新增节点 */
            if (currentNode.data == null) {
                if (frq == 0)
                    frq = 1;
                WordEntry word = new WordEntry(key);
				/* 给新增加词id */
                word.biEntry.id = id;
                id++; // 增加词编号
				/* 统计同一个词的各种词性及对应频率 */
                word.posInf.put(code, frq);
                currentNode.data = word;
            } else {
				/* 统计同一个词的各种词性及对应频率 */
                currentNode.data.posInf.put(code, frq);
            }
            totalFreq += frq; // 统计词典中总词频
        }
        read.close();
    }

    /* 扫描2元语法分词字典,给字典中的每个节点加上对应前缀的频率 */
    public void loadBigramDictionay(String path) throws Exception {
        System.out.println("loadBigramDictionay");
		/* 从BigramDict.txt中加载数据 */
        String line = null;
        InputStream file = new FileInputStream(new File(path));

        BufferedReader read = new BufferedReader(new InputStreamReader(file,
                "GBK"));

        String strline = null;
        String prefixKey = null;
        String suffixkey = null;
        int id = 0; /* 记录单词的id */
        int frq = 0; /* 记录单词的频率 */
        TSTNode prefixNode = null; /* 前缀节点 */
        TSTNode suffixNode = null; /* 后缀节点 */

        int count = 0;

        while ((line = read.readLine()) != null) {
            count++;
            if (count % 100000 == 1) {
                System.out.println("loadBigramDictionay " + line);
            }
            // System.out.println("loadBigramDictionay "+line);
            StringTokenizer st = new StringTokenizer(line, ":");
            strline = st.nextToken();

            int pos = strline.indexOf("@");
            if (pos < 0) {
                read.close();
                throw new Exception("格式错误：" + line);
            }
			/* 求得@之前的部分 */
            prefixKey = strline.substring(0, pos);
			/* 求得@之后的部分 */
            suffixkey = strline.substring(pos + 1);

			/* 寻找后缀节点 */
            suffixNode = getNode(suffixkey);
            //System.out.println("b:"+suffixkey);

            if ((suffixNode == null) || (suffixNode.data == null)) {// 在一元词典找后缀节点找不到去掉
				/*
				 * if("北京".equals(suffixkey)){
				 * System.out.println("error suffixNode"); }
				 */
                continue;
            }

			/* 寻找前缀节点 */
            prefixNode = getNode(prefixKey);
            if ((prefixNode == null) || (prefixNode.data == null)) {
				/*
				 * if("北京".equals(suffixkey)){
				 * System.out.println("error suffixNode"); }
				 */
                continue;
            }
            id = prefixNode.data.biEntry.id; /* 记录前缀单词的id */
            frq = Integer.parseInt(st.nextToken()); /* 记录单词的频率 */

            suffixNode.data.biEntry.put(id, frq);
			/*
			 * if("北京".equals(suffixkey)){ System.out.println(line +
			 * " put "+id+":"+frq);
			 * System.out.println(suffixNode.data.biEntry.toString()); }
			 */
        }
        read.close();
        System.out.println("loadBigramDictionay end");
    }

    static final String baseDic = "coreDict.txt";
    static final String bigramDic = "BigramDict.txt";
    public static final String binDic = "SuffixBigram.bin";

    public SuffixBigram(String dicDir) throws Exception {
		/* 先判断二进制文件是否存在，如果不存在则创建该文件 */
        File dataFile = new File(dicDir + binDic);

        if (!dataFile.exists()) {
            loadBaseDictionay(dicDir + baseDic);

			/* 加载二元转移关系字典 */
            loadBigramDictionay(dicDir + bigramDic);

			/* 创建二进制数据文件 */
            createBinaryDataFile(dataFile);

        } else /* 从生成的数组树中进行加载 */
        {
            loadBinaryDataFile(dataFile);
        }
    }

    /* 生成二进制文件 */
    public void createBinaryDataFile(File file) throws IOException,
            URISyntaxException {
        FileOutputStream file_output = new FileOutputStream(file);
        BufferedOutputStream buffer = new BufferedOutputStream(file_output);
        DataOutputStream data_out = new DataOutputStream(buffer);

        try {
            TSTNode currentNode = rootNode;
            // if (currentNode == null)
            // return;
            TSTNode tempNode = null;

            int currNodeCode = 1; /* 当前节点编号 */
            int leftNodeCode = 0; /* 当前节点的左孩子节点编号 */
            int middleNodeCode = 0; /* 当前节点的中间孩子节点编号 */
            int rightNodeCode = 0; /* 当前节点的右孩子节点编号 */
            int tempNodeCode = currNodeCode;
            byte[] by = null;

			/* 用于存放节点数据的队列 */
            Deque<TSTNode> queueNode = new ArrayDeque<TSTNode>();
            queueNode.addFirst(currentNode);

			/* 用于存放节点编号的队列 */
            Deque<Integer> queueNodeIndex = new ArrayDeque<Integer>();
            queueNodeIndex.addFirst(currNodeCode);

            // System.out.println("id:" + nodeId);
            data_out.writeInt(nodeId);// 节点ID
            data_out.writeInt(id);// 词ID
            data_out.writeDouble(totalFreq);

            Charset charset = Charset.forName("utf-8");// 指定字符串编码

			/* 广度优先遍历所有树节点，将其加入至数组中 */
            while (!queueNodeIndex.isEmpty()) {
				/* 取出队列第一个节点 */
                tempNode = queueNode.pollFirst();
                currNodeCode = queueNodeIndex.pollFirst();

				/* 处理左子节点 */
                if (tempNode.loNode != null) {
                    tempNodeCode++;
                    leftNodeCode = tempNodeCode;
                    queueNode.addLast(tempNode.loNode);
                    queueNodeIndex.addLast(leftNodeCode);
                } else {
                    leftNodeCode = 0;
                }

				/* 处理中间子节点 */
                if (tempNode.eqNode != null) {
                    tempNodeCode++;
                    middleNodeCode = tempNodeCode;
                    queueNode.addLast(tempNode.eqNode);
                    queueNodeIndex.addLast(middleNodeCode);
                } else {
                    middleNodeCode = 0;
                }

				/* 处理右子节点 */
                if (tempNode.hiNode != null) {
                    tempNodeCode++;
                    rightNodeCode = tempNodeCode;
                    queueNode.addLast(tempNode.hiNode);
                    queueNodeIndex.addLast(rightNodeCode);
                } else {
                    rightNodeCode = 0;
                }

				/* 写入本节点的编号信息 */
                // System.out.println("currNodeCode :" + currNodeCode);
                data_out.writeInt(currNodeCode);

				/* 写入左孩子节点的编号信息 */
                data_out.writeInt(leftNodeCode);

				/* 写入中孩子节点的编号信息 */
                data_out.writeInt(middleNodeCode);

				/* 写入右孩子节点的编号信息 */
                data_out.writeInt(rightNodeCode);

                by = String.valueOf(tempNode.splitChar).getBytes("UTF-8");// 把分割字符变成UTF-8的字符串

				/* 记录byte数组的长度 */
                data_out.writeInt(by.length);

				/* 写入splitChar */
                data_out.write(by);

				/* 是结束节点,data域不为空 */
                if (tempNode.data != null) {
                    // System.out.println("word:"+new
                    // String(tempNode.data.word));
                    CharBuffer cBuffer = CharBuffer.wrap(tempNode.data.word);
                    ByteBuffer bb = charset.encode(cBuffer);// 得到字符串对应的字节数组，不知道词的长度，用ByteBuffer

					/* 写入词的长度 */
                    data_out.writeInt(bb.limit());

                    // System.out.println("by.length:"+by.length);
					/* 写入词的内容 */
                    for (int i = 0; i < bb.limit(); ++i)
                        data_out.write(bb.get(i));

                    tempNode.data.posInf.save(data_out);
                    tempNode.data.biEntry.save(data_out);
                }
				/* 不是结束节点,data域为空 */
                else {
					/* 写入字符串的长度 */
                    data_out.writeInt(0);
                }
                // System.out.println("queue size:" + queueNodeIndex.size());
            }

            by = null;
            queueNode = null;
            queueNodeIndex = null;

            data_out.close();
            file_output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* 从 二进制文件中加载,创建树节点 */
    public void loadBinaryDataFile(File file) throws IOException {
        Charset charset = Charset.forName("utf-8"); // 得到字符集
        InputStream file_input = new FileInputStream(file);

        String key; /* 记录每一个词语 */

		/* 读取二进制文件 */
        BufferedInputStream buffer = new BufferedInputStream(file_input);
        DataInputStream data_in = new DataInputStream(buffer);

		/* 获取节点id */
        int nodeCount = data_in.readInt();
        // System.out.print("nodeId:" + nodeId);

        TSTNode[] nodeList = new TSTNode[nodeCount + 1];// nodeId从1开始
        // 要预先创建出来所有的节点，因为当前节点要指向后续节点
        for (int i = 0; i < nodeList.length; i++) {
            nodeList[i] = new TSTNode();
        }

		/* 统计词典中词目前的最大id */
        id = data_in.readInt();
        // System.out.println("wordid:" + wordId);

		/* 统计词典中词目前词的个数 */
        totalFreq = data_in.readDouble();
        // System.out.println("wordCount:" + wordCount);

        int index = 1;

        while (index <= nodeCount) {
            int currNodeIndex = data_in.readInt(); /* 获得当前节点编号 */
            int leftNodeIndex = data_in.readInt(); /* 获得当前节点左子节点编号 */
            int middleNodeIndex = data_in.readInt(); /* 获得当前节点中子节点编号 */
            int rightNodeIndex = data_in.readInt(); /* 获得当前节点右子节点编号 */

			/* 获取splitchar值 */
            int length = data_in.readInt();
            byte[] bytebuff = new byte[length];
            data_in.read(bytebuff);

			/* 创建TSTNode节点 */
            nodeList[currNodeIndex].splitChar = charset.decode(
                    ByteBuffer.wrap(bytebuff)).charAt(0);
            bytebuff = null;// 解除引用
            // 获取字典中词的内容
            length = data_in.readInt();

			/* 如果data域不为空则填充数据域 */
            if (length > 0) {
                bytebuff = new byte[length];
                data_in.read(bytebuff);
                key = new String(bytebuff, "UTF-8");// 将字符串赋给key
                bytebuff = null;
                // System.out.println(key);
                WordTypes wordTypes = new WordTypes(data_in);

                BigramMap biEntry = new BigramMap(data_in);

                nodeList[currNodeIndex].data = new WordEntry(key, wordTypes,
                        biEntry);// 写入data
            }

			/* 生成树节点之间的对应关系，左、中、右子树 */
            if (leftNodeIndex >= 0) {
                nodeList[currNodeIndex].loNode = nodeList[leftNodeIndex];// 建立引用关系，下次循环进行内容填充
            }

            if (middleNodeIndex >= 0) {
                nodeList[currNodeIndex].eqNode = nodeList[middleNodeIndex];
            }

            if (rightNodeIndex >= 0) {
                nodeList[currNodeIndex].hiNode = nodeList[rightNodeIndex];
            }

            index++;
        }

        data_in.close();
        buffer.close();
        file_input.close();

        rootNode = nodeList[1];

        return;
    }

    /* 查找词对应的节点 */
    public TSTNode getNode(String key) {
        if (key == null) {
            throw new NullPointerException("空指针异常");
        }
        int charIndex = key.length() - 1;
        TSTNode currentNode = rootNode;

        while (true) {
            if (currentNode == null)
                return null;
            int compa = (key.charAt(charIndex) - currentNode.splitChar);
            if (compa == 0) {
                if (charIndex <= 0) {
                    return currentNode;
                }
                charIndex--;
                currentNode = currentNode.eqNode;
            } else if (compa < 0) {
                currentNode = currentNode.loNode;
            } else {
                currentNode = currentNode.hiNode;
            }
        }
    }

    // 创建一个结点
    public TSTNode getOrCreateNode(String key) throws NullPointerException,
            IllegalArgumentException {
        if (key == null) {
            throw new NullPointerException("空指针异常");
        }
        int charIndex = key.length() - 1;
        TSTNode currentNode = rootNode;
        if (rootNode == null) {
            rootNode = new TSTNode(key.charAt(charIndex));
            nodeId++;
        }
        while (true) {
            int compa = (key.charAt(charIndex) - currentNode.splitChar);
            if (compa == 0) {
                if (charIndex <= 0) {
                    return currentNode;
                }
                charIndex--;
                if (currentNode.eqNode == null) {
                    currentNode.eqNode = new TSTNode(key.charAt(charIndex));
                    nodeId++;
                }
                currentNode = currentNode.eqNode;
            } else if (compa < 0) {
                if (currentNode.loNode == null) {
                    currentNode.loNode = new TSTNode(key.charAt(charIndex));
                    nodeId++;
                }
                currentNode = currentNode.loNode;
            } else {
                if (currentNode.hiNode == null) {
                    currentNode.hiNode = new TSTNode(key.charAt(charIndex));
                    nodeId++;
                }
                currentNode = currentNode.hiNode;
            }
        }
    }

    // 如果匹配上则返回true，否则返回false
    public boolean matchAll(String sentence, int offset, ArrayList<WordEntry> ret) {
        ret.clear(); // 清空返回数组中的词
        if (sentence == null || rootNode == null || "".equals(sentence)) {
            return false;
        }
        // TODO:处理英文串和数字串等未登录串

        TSTNode currentNode = rootNode;
        int charIndex = offset;
        while (true) {
            if (currentNode == null) {
				/*if (ret.size() == 0){
					ret.add(new WordEntry(sentence
							.substring(offset, offset + 1)));
				}*/
                //return ;
                if (ret.size() > 0) {
                    return true;
                }
                return false;
            }
            int charComp = sentence.charAt(charIndex) - currentNode.splitChar;
            if (charComp == 0) {
                if (currentNode.data != null) {
                    // System.out.println(currentNode.data) ;
                    ret.add(currentNode.data);
                }

                if (charIndex <= 0) {
					/*if (ret.size() == 0)
						ret.add(new WordEntry(sentence.substring(offset,
								offset + 1)));
					return;*/
                    if (ret.size() > 0) {
                        return true;
                    }
                    return false;
                }
                charIndex--; // 继续往前找
                currentNode = currentNode.eqNode;
            } else if (charComp < 0) {
                currentNode = currentNode.loNode;
            } else {
                currentNode = currentNode.hiNode;
            }
        }
    }

    /* 从二元字典中查找每个词对应的前缀的频率,如果没有对应的前缀则频率返回0 */
    public int getBigramFreq(String prev, String next) {
        TSTNode nextNode = getNode(next);
        if(nextNode==null)
            return 0;
        TSTNode prevNode = getNode(prev);
        if(prevNode==null)
            return 0;

		/* 找到的对象中查找是否有名为prev的前缀及其频率 */
        int frq = nextNode.data.biEntry.get(prevNode.data.biEntry.id);

        if (frq < 0)
            return 0;
        else
            return frq;
    }

    public boolean isExist(String prevWord, String nextWord) {
        TSTNode nextNode = getNode(nextWord);
        if(nextNode==null)
            return false;
        TSTNode prevNode = getNode(prevWord);
        if(prevNode==null)
            return false;

        if (prevNode.data==null)
            return false;
        if(nextNode.data==null)
            return false;

        BigramMap prev =prevNode.data.biEntry;

        BigramMap next =  nextNode.data.biEntry;
        if ((next == null) || (prev == null))
            return false;

		/* 找到的对象中查找是否有名为prev的前缀及其频率 */
        int frq = next.get(prev.id);

        if (frq <= 0)
            return false;

        return true;
    }

    //是否容易和其他各种词搭配
    public boolean isActiveWord(String word) {
        TSTNode node = getNode(word);
        if(node == null)
            return false;

        if(node.data == null)
            return false;

        if(node.data.biEntry == null)
            return false;
        BigramMap biMap = node.data.biEntry;
        if(biMap.prevIds.length>5)
            return true;
        return false;
    }
}
