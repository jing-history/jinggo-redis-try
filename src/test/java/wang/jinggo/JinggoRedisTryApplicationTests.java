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
import redis.clients.jedis.Pipeline;
import wang.jinggo.db.InitDataToDataBase;
import wang.jinggo.domain.Note;
import wang.jinggo.service.BaseService;
import wang.jinggo.service.NoteService;
import wang.jinggo.synchronize.NotifyDataBase;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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

	// 查询单条数据
	// @Test
	public void findOne() {
		String id = "1";
		Note note = noteService.queryById(id);
		logger.info(note.toString());
	}

	@Test
	public void insert() throws Exception {
		Note note = new Note();
		note.setFlag(0);
		note.setFromUrl("www.ggjlovezjy.com:1314");
		note.setNoteName("测试插入");
		note.setAuthorName("11111测试插入");
		// baseService.insert(note);

		// List<Object> noteList = new ArrayList<Object>();
		// noteList.add(note);
		// Transaction tx =
		// redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString()).getResource().multi();
		//
		// RedisDao da = new RedisDao(tx);
		// da.insertListToredis(noteList);
		// tx.exec();

	}

	// 查询带参数的
	// @Test
	public void findByParam() {
		Note note = new Note();
		note.setAuthorName("张静月");
		note.setFromUrl("http://www.tuicool.com/");
		List<Note> noteList = noteService.queryParamAnd(note);

		for (Note list : noteList) {
			logger.info(list.toString());
		}
	}

	/**
	 * @Description: 测试删除
	 */
	// @Test
	public void delete() {
		for (int i = 0; i < 2; i++) {
			baseService.delete(i + "");
		}
	}

	/**
	 * @Description: 测试更新。更新需要注意的细节就是，先从redis里面查询出来的值，然后在上面做修改。
	 */
	// @Test
	public void update() {
		String id = "2";
		Note note = noteService.queryById(id);
		note.setAuthorName("张静月");
		note.setFromUrl("www.ggjlovezjy.com:1314");
		baseService.update(note);
	}

	// @Test
	public void after() {
		RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
		Jedis jedis = pool.getResource();
		logger.info("======删除之后打印===========");
		display(jedis);
		pool.releaseResource(jedis);
	}

	@Test
	public void pipeline() {
		RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
		Jedis jedis = pool.getResource();
		Pipeline pipeline = jedis.pipelined();
		pipeline.set("jinggo", "jj123456");
		pipeline.incr("counter");
		List<Object> resultList = pipeline.syncAndReturnAll();
		for (Object object : resultList) {
			System.out.println(object);
		}
	}

	@Test
	public void luaScrpit() throws InterruptedException {
		RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
		Jedis jedis = pool.getResource();
		String key = "hello";
//		String script = "return redis.call('get',KEYS[1])";
//		Object reuslt = jedis.eval(script,1,key);
//		logger.info("reuslt:	" + reuslt);
		System.out.println(jedis.get(key));
		TimeUnit.SECONDS.sleep(10);
		System.out.println(jedis.ping());
		TimeUnit.SECONDS.sleep(5);
		pool.releaseResource(jedis);
	}

	private void display(Jedis jedis) {
		Set<String> aa = jedis.smembers("Note:createdate:2015-05-20 01:04:13.0");
		Set<String> bb = jedis.smembers("Note:fromUrl:http://www.tuicool.com/articles/vquaei");
		Set<String> cc = jedis.smembers("Note:flag:0");
		Set<String> dd = jedis.smembers("Note:authorName:高广金");
		for (String string1 : aa) {
			logger.info("验证a" + string1);// 日期有重复的
		}
		for (String string2 : bb) {
			logger.info("验证b" + string2);
		}
		for (String string3 : cc) {
			logger.info("验证c" + string3);
		}
		for (String string4 : dd) {
			logger.info("验证d" + string4);
		}
	}
}
