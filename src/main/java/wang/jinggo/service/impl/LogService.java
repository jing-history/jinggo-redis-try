package wang.jinggo.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.Log;
import wang.jinggo.pojo.SearchVo;

/**
 * 日志接口
 */
public interface LogService extends XbootBaseService<Log,String> {

    /**
     * 日志搜索
     * @param key
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<Log> searchLog(String key, SearchVo searchVo, Pageable pageable);

    /**
     * 删除所有
     */
    void deleteAll();
}
