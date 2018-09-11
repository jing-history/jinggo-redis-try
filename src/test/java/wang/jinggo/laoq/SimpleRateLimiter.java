package wang.jinggo.laoq;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import wang.jinggo.synchronize.NotifyDataBase;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.io.IOException;

/**
 * redis 来实现简单限流策略
 * @author wangyj
 * @description
 * @create 2018-08-20 17:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleRateLimiter {

    @Autowired
    private RedisCacheManager redisCacheManager;
    private Jedis jedis ;

    @Before
    public void before(){
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        jedis = pool.getResource();
    }

    @Test
    public void doing() throws IOException {
        for(int i=0;i<20;i++) {
            System.out.println(isActionAllowed("jinggo", "reply", 60, 5));
        }
    }

    private boolean isActionAllowed(String userId, String actionKey, int period, int maxCount) throws IOException {
        String key = String.format("hist:%s:%s", userId, actionKey);
        long nowTs = System.currentTimeMillis();
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.zadd(key, nowTs, "" + nowTs);
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        Response<Long> count  =pipe.zcard(key);
        pipe.expire(key, period + 1);
        pipe.exec();
        pipe.close();
        return count.get() <= maxCount;
    }
}
