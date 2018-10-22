package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.QuartzJob;

import java.util.List;

/**
 * 定时任务数据处理层
 * @author wangyj
 * @description
 * @create 2018-10-22 15:26
 **/
public interface QuartzJobDao extends XbootBaseDao<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}
