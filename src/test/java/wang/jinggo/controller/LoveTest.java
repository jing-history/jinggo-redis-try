package wang.jinggo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import wang.jinggo.dao.LoveRepository;
import wang.jinggo.dao.MusicLrcDao;
import wang.jinggo.domain.MusicLrc;

import javax.servlet.http.Cookie;
import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest
public class LoveTest {

    @Autowired
    private MockMvc mockMvc;

/*    @MockBean
    LoveRepository loveRepository;*/
    @Autowired
    MusicLrcDao musicLrcDao;

    @Test
    public void getLovesTest() throws Exception {
        int userId = 10;
        int expectedCredit = 100;
        // anyInt()
    //    given(this.loveRepository.findAll().size()).willReturn(expectedCredit);
        // todo mvc 调用 content 是返回结果 但是我这里是要返回集合
        mockMvc.perform(get("/love")).andExpect(content().string(expectedCredit+""));

        //使用LinkedMultiValueMap 构造参数
        LinkedMultiValueMap param = new LinkedMultiValueMap();
        param.put("m1", Collections.singletonList("d1"));
        param.put("m2", Collections.singletonList("d2"));

        mockMvc.perform(get("/love").sessionAttr("name","value"));
        mockMvc.perform(get("/love").cookie(new Cookie("name","value")));

        //设置Http Body内容，比如提交的json
        String json = "";
        mockMvc.perform(get("/love").content(json));

        //设置Http header
        mockMvc.perform(get("/love/{id}",userId)
                .contentType("application/x-www-form-urlencoded")   //http 提交的内容
                .accept("application/json") //期望返回内容
                .header("header1","value1"));

        //Json 比较
        String path = "$.success";
        //代码期望返回的json的success属性是true，$代表了Json 的根节点
        mockMvc.perform(get("/love/{id}",userId)).andExpect(jsonPath(path).value(true));
    }

    @Test
    public void getMusicLrc() throws Exception {
        MusicLrc musicLrc = musicLrcDao.findByName("咱们结婚吧");
        System.out.println(musicLrc.toString());
    }
}
