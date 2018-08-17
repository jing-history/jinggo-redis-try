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
import wang.jinggo.dao.NoteRepository;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.Field;
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
                List<Object> listObject = getData(clzz);
                if (clzz.getAnnotation(RedisCache.class) != null) {
                 //   List<Object> listObject = getData(clzz);
                //    RedisDao rd = new RedisDao(tx);
                //    rd.insertListToredis(listObject);
                }
            }
        }catch (Exception e){
            pool.releaseResource(jedis);
            logger.error("" + e.getLocalizedMessage());
        }
    }

    private List<Object> getData(Class<?> clzz) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        Field field = clzz.getDeclaredField("className");
        field.setAccessible(true);
        String tableName = field.get(clzz).toString();
        javax.persistence.Query query = em.createNativeQuery("from " + tableName, Object.class);
        List<Object> resultList = query.getResultList();
        System.out.printf("resultList==>>" + resultList.size());
        if (resultList == null || resultList.size() < 1) {
            return null;
        }
        return resultList;
        /*Field field = clzz.getDeclaredField("className");
        field.setAccessible(true);
        String tableName = field.get(clzz).toString();
        final Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from " + tableName);
        List<Object> objectList = query.list();
        System.out.printf("objectList==>>" + objectList.size());*/
    }
}
