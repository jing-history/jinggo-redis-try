package wang.jinggo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.domain.UserRole;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    private UserRoleService userRoleService;


    @Test
    public void userService(){
        int userId = 10;
        int expectedCredit = 100;
        // anyInt() 任意参数
        given(this.userRoleService.hashCode()).willReturn(expectedCredit);
        int credit = userService.hashCode();
        assertEquals(expectedCredit,credit);
    }
}
