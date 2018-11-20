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
import wang.jinggo.dao.MusicLrcDao;
import wang.jinggo.domain.MusicLrc;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.service.MusicService;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-11-20 15:36
 **/
@Slf4j
@Service
@Transactional
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicLrcDao musicLrcDao;

    @Override
    public MusicLrcDao getRepository() {
        return musicLrcDao;
    }

    @Override
    public Page<MusicLrc> findByCondition(SearchVo searchVo, Pageable pageable) {
        return musicLrcDao.findAll(new Specification<MusicLrc>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<MusicLrc> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {

                Path<String> titleField = root.get("name");
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
