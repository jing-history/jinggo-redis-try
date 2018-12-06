package wang.jinggo.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.dao.blog.ArticleListDao;
import wang.jinggo.domain.blog.B3SoloArticle;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-12-06 11:01
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleListTest {

    @Autowired
    private ArticleListDao articleListDao;

    @Test
    public void getArticlesAll(){
        List<B3SoloArticle> articles =  articleListDao.findAll();
        System.out.println(articles.toString());
    }
}
