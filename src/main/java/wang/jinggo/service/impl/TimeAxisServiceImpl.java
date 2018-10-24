package wang.jinggo.service.impl;

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
import wang.jinggo.dao.TimeAxisDao;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.service.TimeAxisService;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-10-23 18:09
 **/
@Slf4j
@Service
@Transactional
public class TimeAxisServiceImpl implements TimeAxisService {

    @Autowired
    private TimeAxisDao timeAxisDao;

    @Override
    public TimeAxisDao getRepository() {
        return timeAxisDao;
    }

    @Override
    public Page<TimeAxis> findByCondition(SearchVo searchVo, Pageable pageable) {
        return timeAxisDao.findAll(new Specification<TimeAxis>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<TimeAxis> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

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
