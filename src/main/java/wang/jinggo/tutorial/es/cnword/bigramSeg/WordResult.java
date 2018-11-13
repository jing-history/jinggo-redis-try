/*
 * Created on 2004-8-27
 *
 */
package wang.jinggo.tutorial.es.cnword.bigramSeg;

/**
 *
 * the word and it's fee
 * change history:
 * add start position and end position
 */
public class WordResult implements Comparable<WordResult>{
	/**	The word */
	public String word;
	/**  semantic code  */
	public String code;
	/**	the POS of the word */
	public short pos;
//	public CoreData candidatePOS;
	/** The -log(frequency/MAX) */
	public double fee;
	
	public int end;
	
	public WordResult(String word,short pos)
	{
		this.word = word;
		this.pos = pos;
	//	this.candidatePOS = cPOS;
	}
	
	public WordResult(String word,short pos,double value,int offset,String c)
	{
		this.word = word;
		this.pos = pos;
	//	candidatePOS = cPOS;
		fee = value;
		end = offset;
		code = c;
	}
	
	public int compareTo(WordResult that){
		int ret = word.compareTo(that.word);
		
		if (ret!= 0 )
	         return ret;
	      
		return (pos - that.pos);
	}
	
	public String toString(){
		return "word=\""+word+"\";	pos="+pos+";	fee="+fee+";	end="+end;
	}
}
