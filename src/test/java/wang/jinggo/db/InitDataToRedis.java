package wang.jinggo.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wang.jinggo.JinggoRedisTryApplication;

import javax.transaction.Transactional;
import java.io.IOException;

/**
 * 初始化数据库数据到redis
 * @author wangyj
 * @description
 * @create 2018-08-16 14:37
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JinggoRedisTryApplication.class)
public class InitDataToRedis {

    @Test
    @Transactional
    public void init() throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException {

    }
}
