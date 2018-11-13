/*
 * Created on 2005-9-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package wang.jinggo.tutorial.es.cnword.bigramSeg;

/**
 *
 * 有向边，边的权重不能?歉菏?
 * 
 * This class models an edge from vertex X to vertex Y -- pretty
 * straightforward. 
 *
 * It is a little wasteful to keep the starting vertex, since the 
 * adjacency list will do this for us -- but it makes the code
 * neater in other places (makes the Edge independent of the Adj. List
*/
public class CnToken {
	public String termText;
	public int start;
	public int end;
	public int freq;
	public WordTypes typecount; /* 各种词的词性及相关频率 */
	public BigramMap biEntry;
	
	public CnToken(int vertexFrom, int vertexTo, int f, String word, BigramMap entry, WordTypes types)
	{
		start = vertexFrom;
		end = vertexTo;
		termText = word;
		freq = f;
		biEntry = entry;
		typecount = types;
	}
	
	public String toString()
	{
		return "text:"+termText+" start:"+start+" end:"+end+" cost:"+freq;
	}
}