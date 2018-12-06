package wang.jinggo.service.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.blog.B3SoloArticle;
import wang.jinggo.pojo.SearchVo;

/**
 * Created by gz12 on 2018-12-06.
 */
public interface ArticleListService extends XbootBaseService {

    /**
     *  条件查询
     * title
     * createDate
     * @param pageable
     * @return
     */
    Page<B3SoloArticle> findByCondition(SearchVo searchVo, Pageable pageable);
}
