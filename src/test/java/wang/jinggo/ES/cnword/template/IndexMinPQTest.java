package wang.jinggo.ES.cnword.template;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.lietu.template.IndexMinPQ;

public class IndexMinPQTest {
	public static final Map<String, Integer> map = new TreeMap<String, Integer>();
    static{
            map.put("E",0);
            map.put("D",2);
            map.put("C",3);
            map.put("Z",4);
            map.put("A",5);
    }
    
    public static void main(String[] args){
            IndexMinPQ<String> indexMinPQ = new IndexMinPQ<String>(7);
            for(Entry<String, Integer> entry:map.entrySet()){
                    indexMinPQ.insert(entry.getValue(), entry.getKey());
            }
            
            System.out.println("minIndex:"+indexMinPQ.minIndex());
            //Assert.assertEquals(map.get("A").intValue(), indexMinPQ.delMin());
            System.out.println(indexMinPQ.delMin());
            System.out.println(indexMinPQ.currentKey);
    }
}
