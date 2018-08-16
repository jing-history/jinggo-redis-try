package wang.jinggo.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import wang.jinggo.JinggoRedisTryApplication;
import wang.jinggo.dao.NoteRepository;
import wang.jinggo.domain.Note;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;

/**
 * 初始化网站数据到数据库
 * @author wangyj
 * @description
 * @create 2018-08-15 14:49
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JinggoRedisTryApplication.class)
public class InitDataToDataBase {

    private static final Logger logger = LoggerFactory.getLogger(InitDataToDataBase.class);
    // 存放url 和title
    public ConcurrentHashMap<String, String> currentHashMap = new ConcurrentHashMap<String, String>();
    @Autowired
    private NoteRepository noteRepository ;

    @Test
    @Transactional
    public void test() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        try{
            for (int i = 0; i < 2; i++) {// TODO 总共20也 开启20个线程去爬去链接 先改成2个
                pool.execute(new PutArticelUrlByPage(currentHashMap, i));
            }
            pool.shutdown();
            pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);// 线程池里面线程所有执行完之后执行下面

            // 处理map里面的链接，保存到数据库。
            System.out.println("map大小：" + currentHashMap.size());
            long beginTime = System.currentTimeMillis();

            // 【2】多线程插入、CountDownLatch是用来解决防止session关闭的问题
            CountDownLatch latch = new CountDownLatch(currentHashMap.size());
            List<Note> listNote = new StoreToDataBaseByThread(currentHashMap, latch).insertToDatabase();
            latch.await();
            System.out.println("listNote大小：" + listNote.size());
            // 因为用的是durid连接池，获取数据库连接和创建jdbc 必须要在一个线程里面。所以采用串行保存
            noteRepository.saveAll(listNote);

            long endTime = System.currentTimeMillis();
            logger.info("插入数据库耗时：" + (endTime - beginTime) + "ms");

            System.out.println("end");
        }catch (Exception e){
            logger.error("" + e.getLocalizedMessage());
        }
    }
}
