package wang.jinggo.service.blog;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import wang.jinggo.dao.blog.ArticleListDao;
import wang.jinggo.domain.blog.BlogArticle;
import wang.jinggo.pojo.SearchVo;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-12-06 10:50
 **/
@Slf4j
@Service
@Transactional
public class ArticleListServiceImpl implements ArticleListService {

    @Autowired
    private ArticleListDao articleListDao;

    @Override
    public ArticleListDao getRepository() {
        return articleListDao;
    }

    @Override
    public Page<BlogArticle> findByCondition(SearchVo searchVo, Pageable pageable) {
        return articleListDao.findAll(new Specification<BlogArticle>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<BlogArticle> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("articleTitle");
                Path<Date> createTimeField=root.get("articleCreateDate");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                if(StrUtil.isNotBlank(searchVo.getTitle())){
                    list.add(cb.like(titleField,'%'+searchVo.getTitle()+'%'));
                }
                //创建时间
                if(StrUtil.isNotBlank(searchVo.getStartDate())){
                    Date start = DateUtil.parse(searchVo.getStartDate());
                    list.add(cb.equal(createTimeField, start));
                }
                Predicate[] arr = new Predicate[list.size()];
                cq.where(list.toArray(arr));
                return null;
            }
        }, pageable);
    }

}
