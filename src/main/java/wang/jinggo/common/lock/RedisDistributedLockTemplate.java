package wang.jinggo.common.lock;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-18 17:20
 **/
@Slf4j
@Component
public class RedisDistributedLockTemplate implements DistributedLockTemplate {

    @Autowired
    RedisCacheManager redisCacheManager;

    @Override
    public Object execute(String lockId, Integer timeout, Callback callback) {

        RedisReentrantLock distributedReentrantLock = null;
        boolean getLock=false;
        try {
            RedisCachePool jedisPool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            distributedReentrantLock = new RedisReentrantLock(jedisPool,lockId);
            if(distributedReentrantLock.tryLock(new Long(timeout), TimeUnit.MILLISECONDS)){
                getLock=true;
                return callback.onGetLock();
            }else{
                return callback.onTimeout();
            }
        }catch(InterruptedException ex){
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }finally {
            if(getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
