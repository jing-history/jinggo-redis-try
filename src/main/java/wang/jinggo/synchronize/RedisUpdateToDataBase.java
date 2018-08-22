package wang.jinggo.synchronize;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * redis异步更新到数据库
 * @author wangyj
 * @description
 * @create 2018-08-21 17:55
 **/
@Transactional
public class RedisUpdateToDataBase {

    public boolean excuteUpdate(List<String> list) {
        return false;
    }
}
