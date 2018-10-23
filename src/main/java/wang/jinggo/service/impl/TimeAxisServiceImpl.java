package wang.jinggo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.TimeAxisDao;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.service.TimeAxisService;

import javax.persistence.criteria.*;

/**
 * @author wangyj
 * @description
 * @create 2018-10-23 18:09
 **/
public class TimeAxisServiceImpl implements TimeAxisService {

    @Autowired
    private TimeAxisDao timeAxisDao;

    @Override
    public TimeAxisDao getRepository() {
        return timeAxisDao;
    }

    @Override
    public Page<TimeAxis> findByCondition(String title, String createDate, Pageable pageable) {
        /*return timeAxisDao.findAll(new Specification<TimeAxis>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<TimeAxis> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                Path<String> titleField = root.get("title");
                Path<String> createdAtField = root.get("createdAt");
                return null;
            }
        }*/
        return null;
    }
}
