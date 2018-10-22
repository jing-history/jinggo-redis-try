package wang.jinggo.service;

import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.QuartzJob;

import java.util.List;

/**
 * 定时任务接口
 * @author wangyj
 * @description
 * @create 2018-10-22 14:59
 **/
public interface QuartzJobService extends XbootBaseService<QuartzJob,String> {

    /**
     * 通过类名获取
     * @param jobClassName
     * @return
     */
    List<QuartzJob> findByJobClassName(String jobClassName);
}
