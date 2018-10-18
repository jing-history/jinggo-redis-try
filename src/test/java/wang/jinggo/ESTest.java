package wang.jinggo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import wang.jinggo.synchronize.NotifyDataBase;
import wang.jinggo.util.ElasticsearchUtils;
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
public class ESTest {

    @Autowired
    ElasticsearchUtils elasticsearchUtils;

    @Test
    public void addEs(){

    }
}
