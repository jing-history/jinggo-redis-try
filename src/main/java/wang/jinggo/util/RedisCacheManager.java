package wang.jinggo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangyj
 * @description
 * @create 2018-08-16 14:39
 **/
@Component
@PropertySource(value = "classpath:redis.properties")
public class RedisCacheManager {

    @Value("${redisdbtype}")
    private String redisdbtype;

    @Value("${redisdbnumber}")
    private String redisdbnumber;

    @Value("${host}")
    private String host;
    @Value("${port}")
    private int port;
    @Value("${timeout}")
    private int timeout;
    @Value("${passwords}")
    private String passwords;

    @Value("${maxtotal}")
    private String maxtotal;
    @Value("${maxidle}")
    private String maxidle;
    @Value("${minidle}")
    private String minidle;
    @Value("${maxwaitmillis}")
    private String maxwaitmillis;
    @Value("${testonborrow}")
    private String testonborrow;
    @Value("${testwhileidle}")
    private String testwhileidle;

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);

    private static JedisPoolConfig poolConfig = null;

    // 保存不同的数据库连接
    private ConcurrentHashMap<String, RedisCachePool> redisPoolMap = new ConcurrentHashMap<String, RedisCachePool>();

    public ConcurrentHashMap<String, RedisCachePool> getRedisPoolMap() {
        if (redisPoolMap.size() < 1) {
            initConfig();
            initPoolMap();
        }
        return redisPoolMap;
    }

    /**
     * @Description:共享的poolconfig
     * @return:void
     */
    private void initConfig() {
        poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(testwhileidle.equals("true") ? true : false);
        poolConfig.setTestWhileIdle(testonborrow.equals("true") ? true : false);
        poolConfig.setMaxIdle(Integer.parseInt(maxidle));
        poolConfig.setMaxTotal(Integer.parseInt(maxtotal));
        poolConfig.setMinIdle(Integer.parseInt(minidle));
        poolConfig.setMaxWaitMillis(Integer.parseInt(maxwaitmillis));
    }

    private void initPoolMap() {
        try {
            if (null != redisdbtype && null != redisdbnumber) {
                String[] dbs = redisdbtype.split(",");
                String[] numbers = redisdbnumber.split(",");
                for (int i = 0; i < dbs.length; i++) {
                    // 得到redis连接池对象
                    JedisPool jedisPool = null;
                    if(StringUtils.hasText(passwords)){
                        jedisPool = new JedisPool(poolConfig, host, port, timeout, passwords);
                    }else {
                        jedisPool = new JedisPool(poolConfig, host, port, timeout);
                    }
                    // 存放不同redis数据库
                    redisPoolMap.put(dbs[i], new RedisCachePool(jedisPool, Integer.parseInt(numbers[i])));
                }
            }
        } catch (Exception e) {
            logger.error("redisCacheManager初始化失败！" + e.getLocalizedMessage());
        }
    }

    /**
     * @Description: 得到jedis连接
     * @param dbtypeName
     * @return:Jedis
     */
    public Jedis getResource(RedisDataBaseType dbtypeName) {
        Jedis jedisResource = null;
        RedisCachePool pool = redisPoolMap.get(dbtypeName.toString());
        if (pool != null) {
            jedisResource = pool.getResource();
        }
        return jedisResource;
    }

    /**
     * @Description: 返回连接池
     * @param dbtypeName
     * @param jedis
     * @return:void
     */
    public void returnResource(RedisDataBaseType dbtypeName, Jedis jedis) {
        RedisCachePool pool = redisPoolMap.get(dbtypeName.toString());
        if (pool != null)
            pool.releaseResource(jedis);
    }
}
