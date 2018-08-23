package wang.jinggo.queue;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

/**
 * @author wangyj
 * @description
 * @create 2018-08-23 11:55
 **/
public class SubThread extends Thread {

    RedisCachePool pool = null;
    private final Subscriber subscriber = new Subscriber();

    private final String channel = "mychannel";

    public SubThread(RedisCacheManager redisCacheManager) {
        this.pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());;
    }

    @Override
    public void run() {
        System.out.println(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            System.out.println(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
