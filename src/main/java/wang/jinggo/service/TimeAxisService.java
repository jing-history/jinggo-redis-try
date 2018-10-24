package wang.jinggo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.domain.User;
import wang.jinggo.pojo.SearchVo;

/**
 * @author wangyj
 * @description
 * @create 2018-10-23 17:49
 **/
public interface TimeAxisService  extends XbootBaseService<TimeAxis,String> {

    /**
     *  条件查询
     * @param title
     * @param createDate
     * @param pageable
     * @return
     */
    Page<TimeAxis> findByCondition(SearchVo searchVo, Pageable pageable);
}
