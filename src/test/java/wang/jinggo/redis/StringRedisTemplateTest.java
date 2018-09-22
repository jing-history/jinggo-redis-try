package wang.jinggo.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-22 16:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void springTemp(){
        String flagKey = "loginFailFlag:jinggo";
        redisTemplate.opsForValue().set(flagKey,"vaaskdasj");
    }
}
