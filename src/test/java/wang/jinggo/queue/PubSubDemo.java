package wang.jinggo.queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wang.jinggo.JinggoRedisTryApplication;
import wang.jinggo.util.RedisCacheManager;

/**
 * https://blog.csdn.net/lihao21/article/details/48370687
 * @author wangyj
 * @description 由于Jedis不是线程安全的，JedisPool是线程安全的
 * @create 2018-08-23 14:25
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JinggoRedisTryApplication.class)
public class PubSubDemo {

    @Autowired
    private RedisCacheManager redisCacheManager;

    @Test
    public void testPub(){

        SubThread subThread = new SubThread(redisCacheManager);
        subThread.start();
    }
}
