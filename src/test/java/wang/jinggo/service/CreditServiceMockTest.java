package wang.jinggo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.List;

import static com.sun.tools.doclint.Entity.lt;
import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.lt;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditServiceMockTest {

    @Test
    public void test(){
        int userId = 10;
        //创建Mock 对象
        CreditSystemService creditSystemService = mock(CreditSystemService.class);
        //模拟Mock 对象调用，传入任何int 值都将返回100 积分
        when(creditSystemService.getUserCredit(anyInt())).thenReturn(1000);
        //传入参数必须是10 ，creditSystemService.getUserCredit(10); 需要传入10
        when(creditSystemService.getUserCredit(eq(userId))).thenReturn(1000);

        //实际调用
        int ret = creditSystemService.getUserCredit(10);
        //比较期望值
        assertEquals(1000,ret);

        //mock list
        LinkedList linkedList = mock(LinkedList.class);
        List list = mock(List.class);

        //verity 精确校验
        verify(creditSystemService,times(2)).getUserCredit(eq(userId));

        when(creditSystemService.getUserCredit(lt(0))).thenThrow(new IllegalArgumentException("userId不能小宇0"));

        //XLSUnit 测试方法的使用
    }
}