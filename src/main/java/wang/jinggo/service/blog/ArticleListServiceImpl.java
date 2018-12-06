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
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.blog.ArticleListDao;
import wang.jinggo.domain.blog.B3SoloArticle;
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
    public XbootBaseDao getRepository() {
        return articleListDao;
    }

    @Override
    public Page<B3SoloArticle> findByCondition(SearchVo searchVo, Pageable pageable) {
        return articleListDao.findAll(new Specification<B3SoloArticle>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<B3SoloArticle> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("title");
                Path<Date> createTimeField=root.get("createTime");

                List<Predicate> list = new ArrayList<Predicate>();

                //模糊搜素
                list.add(cb.like(titleField,'%'+searchVo.getTitle()+'%'));
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
