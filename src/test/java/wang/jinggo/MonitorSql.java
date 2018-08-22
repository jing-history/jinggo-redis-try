package wang.jinggo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import wang.jinggo.synchronize.NotifyDataBase;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

/**
 * 控对redis的更新和删除
 * @author wangyj
 * @description
 * @create 2018-08-20 17:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MonitorSql {

    @Autowired
    private RedisCacheManager redisCacheManager;
    @Autowired
    private NotifyDataBase notifyDataBase;

    @Test
    public void monitor(){
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        final Jedis jedis = pool.getResource();

        System.out.println("MonitorSql.main:" + redisCacheManager.toString());
        new Thread() {
            public void run() {// 会广播形式打印log日志
                jedis.subscribe(notifyDataBase,"publog");
            }
        }.start();
    }
}
