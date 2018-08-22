package wang.jinggo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import wang.jinggo.dao.RedisDao;
import wang.jinggo.domain.Note;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author wangyj
 * @description
 * @create 2018-08-22 16:30
 **/
@Service
public class NoteServiceImpl implements NoteService, BaseService<Note> {

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);

    @Autowired
    RedisCacheManager redisCacheManager;

    @Override
    public List<Note> findAll() {

        List<Note> noteList = new ArrayList<Note>();
        RedisCachePool pool = null;
        Jedis jedis = null;
        try {
            pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
            jedis = pool.getResource();
            // 查询不用开启事物
            RedisDao rd = new RedisDao(jedis);
            Set<String> sortKey = rd.smembers("Note:index:noteId");
            noteList = (List<Note>) rd.getListBean(sortKey, Note.class, jedis);

            // dubbo 调用的时候防止java.sql.Blob cannot be assigned from null ，也就是blob字段不能为空
            delalBlob(noteList);
        } catch (Exception e) {
            logger.error(" List<Note> findAll()查询失败！" + e.getLocalizedMessage());
        }
        finally {
            logger.info("回收jedis连接");
            pool.releaseResource(jedis);
        }
        return noteList;
    }

    private void delalBlob(List<Note> noteList) {
        for (Note note : noteList) {
            note.setBlobContent(null);
        }
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Note note) {

    }

    @Override
    public void insert(Note note) {

    }

    @Override
    public Note queryById(String i) {
        return null;
    }

    @Override
    public List<Note> queryParamAnd(Note note) {
        return null;
    }
}
