package wang.jinggo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author wangyj
 * @description
 * @create 2018-08-16 15:13
 **/
public class RedisCachePool {

    private JedisPool jedisPool = null;
    private Integer db;

    public RedisCachePool(JedisPool jedisPool, Integer db) {
        this.jedisPool = jedisPool;
        this.db = db;
    }

    public Jedis getResource(){
        Jedis jedisInstance = null;
        if(jedisPool != null){
            jedisInstance = jedisPool.getResource();
            //每次获得连接之前，切换到对应的数据库。默认的是0
            if(db > 0){
                jedisInstance.select(db);
            }
        }
        return jedisInstance;
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    public void releaseResource(final Jedis jedis) {
        if(jedis != null){
            jedisPool.returnResource(jedis);
        }
    }
}
