package wang.jinggo.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wangyj
 * @description
 * @create 2018-10-18 16:27
 **/
@Data
public class EsPage {

    // 查询es结果
    private long recordCount; // 总记录数
    private List<Map<String, Object>> recordList; // 本页的数据列表
    private List<String> ids;//结果id集合

    public List<String> getIds() {
        return ids;
    }
}
