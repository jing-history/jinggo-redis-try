package wang.jinggo.synchronize;

import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

/**
 * @author wangyj
 * @description pub/sub 异步调用类
 * @create 2018-08-20 17:33
 **/
@Component
public class NotifyDataBase extends JedisPubSub {

}
