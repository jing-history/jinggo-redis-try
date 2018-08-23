package wang.jinggo.queue;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 发布
 * @author wangyj
 * @description
 * @create 2018-08-23 14:22
 **/
public class Publisher {

    RedisCachePool pool = null;

    public Publisher(RedisCacheManager redisCacheManager) {
        this.pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());;
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = pool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("mychannel", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
