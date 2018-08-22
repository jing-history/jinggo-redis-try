package wang.jinggo.synchronize;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import wang.jinggo.config.DruidConfiguration;
import wang.jinggo.dao.RedisDao;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @description pub/sub 异步调用类
 * @create 2018-08-20 17:33
 **/
@Component
public class NotifyDataBase extends JedisPubSub {

    private final static Logger LOG =  LoggerFactory.getLogger(NotifyDataBase.class);

/*    @Autowired
    RedisUpdateToDataBase redisUpdateToDataBase;*/

    @Autowired
    RedisCacheManager redisCacheManager;

    @Override
    public void onMessage(String channel, String sql) {
        LOG.info("redis更新转换成数据库==》sql:" + sql);
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        final Jedis jedis = pool.getResource();

        Long length = jedis.llen(RedisDao.LOG);
        int n = 2;// 如果log的list size 达到n 的时候就一次性执行更新。测试的时候就弄成2
        List<String> list = new ArrayList<String>();
        if (length >= n) {
            for (int i = 0; i < n; i++) {
                String sqlStr = jedis.lpop(RedisDao.LOG);// 删除list首元素
                list.add(sqlStr);
            }

            // 是否执行成功
            boolean flag = true;//redisUpdateToDataBase.excuteUpdate(list);
            if (!flag) {
                for (String oldSql : list) {
                    jedis.lpush(RedisDao.LOG, oldSql);// 更新失败重新添加到list里面
                }
            }
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        LOG.info("onPMessage");
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        LOG.info("开始监控redis变化！");
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        LOG.info("onUnsubscribe");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        LOG.info("onPUnsubscribe");
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        LOG.info("onPSubscribe");
    }
}
