package wang.jinggo.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import wang.jinggo.JinggoRedisTryApplication;
import wang.jinggo.annation.RedisCache;
import wang.jinggo.dao.RedisDao;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 初始化数据库数据到redis
 * @author wangyj
 * @description
 * @create 2018-08-16 14:37
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JinggoRedisTryApplication.class)
public class InitDataToRedis {

    @Autowired
    private RedisCacheManager redisCacheManager;
    private static final Logger logger = LoggerFactory.getLogger(InitDataToRedis.class);

    @PersistenceContext
    EntityManager em;

    @Test
    //@Transactional
    public void init() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException {
        Jedis jedis = null;
        RedisCachePool pool = null;
        try{
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();
            Transaction tx = jedis.multi();
            tx.flushDB();// 清空所有数据
            ResourcePatternResolver rp = new PathMatchingResourcePatternResolver();
            Resource[] resources = rp.getResources("classpath:wang/jinggo/domain/*.class");
            for (Resource resource : resources) {
                String className = resource.getFile().getPath().split("classes\\\\")[1].replaceAll("\\\\", ".").replaceAll(".class", "");
                Class<?> clzz = Thread.currentThread().getContextClassLoader().loadClass(className);
                if (clzz.getAnnotation(RedisCache.class) != null) {
                    List<Object> listObject = getData(clzz);
                    RedisDao rd = new RedisDao(tx);
                    rd.insertListToredis(listObject);
                }
            }
            tx.exec();// 提交事物
        }catch (Exception e){
            pool.releaseResource(jedis);
            logger.error("" + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private List<Object> getData(Class<?> clzz) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field field = clzz.getDeclaredField("className");
        field.setAccessible(true);
        String tableName = field.get(clzz).toString();
        javax.persistence.Query query = em.createQuery("from " + tableName, Object.class);
        List<Object> resultList = query.getResultList();
        if (resultList.size() > 3) {
            resultList = resultList.subList(0, 1);
        }
        if (!tableName.equals("Note")) {
            resultList = new ArrayList<Object>();
        }
        return resultList;
    }
}
