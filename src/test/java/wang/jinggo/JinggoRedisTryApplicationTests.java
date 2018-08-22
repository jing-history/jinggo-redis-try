package wang.jinggo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import wang.jinggo.db.InitDataToDataBase;
import wang.jinggo.domain.Note;
import wang.jinggo.service.BaseService;
import wang.jinggo.service.NoteService;
import wang.jinggo.synchronize.NotifyDataBase;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JinggoRedisTryApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(JinggoRedisTryApplicationTests.class);
	@Autowired
	RedisCacheManager redisCacheManager;
	@Autowired
	BaseService baseService;
	@Autowired
	NoteService noteService;

	// 启动日志监听，用MonitorSql 类代替 因为单元测试里面多线程无法堵塞
	@Before
	public void before() {
		RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
		final Jedis jedis = pool.getResource();
		final JedisPubSub ndb = new NotifyDataBase();
		new Thread() {
			public void run() {// 会广播形式打印log日志
				jedis.subscribe(ndb, "publog");
			}
		}.start();
	}

	// 查询所有数据。redis和服务器子同一局域网下
	@Test
	public void findAll() {
		long time = System.currentTimeMillis();
		List<Note> list = baseService.findAll();

		for (Note note : list) {
			logger.info(note.toString());
		}
		long time2 = System.currentTimeMillis();
		logger.info("耗时" + (time2 - time));// 9790
	}
}
