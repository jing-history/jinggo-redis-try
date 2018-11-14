package wang.jinggo.pojo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author wangyj
 * @description
 * @create 2018-11-14 11:46
 **/
public class ListDesc {
    // list contain  hashmaps
    //every row is a hashmap
    //hashmap  contain "title" -> "news title"
    public ArrayList<HashMap<String, String>> list;

    //result count
    public long count;
    public String suggestedQuery;
}
