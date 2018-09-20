package wang.jinggo.security;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.dao.UserDao;
import wang.jinggo.domain.User;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-20 11:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoadUserByUsernameTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void loadUser(){
        List<User> list=userDao.findByUsernameAndStatus("test", CommonConstant.USER_STATUS_NORMAL);
        log.info(list.toString());
    }
}
