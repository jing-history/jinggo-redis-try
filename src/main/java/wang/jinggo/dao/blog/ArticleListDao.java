package wang.jinggo.dao.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.blog.BlogArticle;

import java.io.Serializable;

/**
 * Created by gz12 on 2018-12-06.
 */
public interface ArticleListDao extends XbootBaseDao<BlogArticle,String> {
}
