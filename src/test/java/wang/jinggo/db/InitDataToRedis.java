package wang.jinggo.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import wang.jinggo.JinggoRedisTryApplication;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import javax.transaction.Transactional;
import java.io.IOException;

/**
 * 初始化数据库数据到redis
 * @author wangyj
 * @description
 * @create 2018-08-16 14:37
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JinggoRedisTryApplication.class)
public class InitDataToRedis {

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Test
    @Transactional
    public void init() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException {
        Jedis jedis = null;
        RedisCachePool pool = null;
        pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
    }
}
