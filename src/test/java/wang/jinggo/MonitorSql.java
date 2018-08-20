package wang.jinggo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import wang.jinggo.config.DruidProperties;
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

    @Test
    public void monitor(){
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        final Jedis jedis = pool.getResource();

        /*final JedisPubSub ndb = (JedisPubSub) application.getBean("notifyDataBase");
        System.out.println("MonitorSql.main:" + redisCacheManager.toString());
        new Thread() {
            public void run() {// 会广播形式打印log日志
                System.out.println("Thread===========>>>" + Thread.currentThread().getName());
            }
        }.start();*/
    }

/*    public static void main(String[] args) {
        AnnotationConfigApplicationContext  application = new AnnotationConfigApplicationContext(JinggoRedisTryApplication.class);
        RedisCacheManager redisCacheManager = (RedisCacheManager) application.getBean("redisCacheManager");
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        System.out.println("MonitorSql.main:" + pool.toString());
    }*/
}
