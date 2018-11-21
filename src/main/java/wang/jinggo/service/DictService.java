package wang.jinggo.service;

import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.Dict;

import java.util.List;

/**
 * 字典接口
 * @author wangyj
 * @description
 * @create 2018-11-21 14:00
 **/
public interface DictService extends XbootBaseService<Dict,String> {

    /**
     * 通过type获取
     * @param type
     * @return
     */
    Dict findByType(String type);
    /**
     * 模糊搜索
     * @param key
     * @return
     */
    List<Dict> findByTitleOrTypeLike(String key);
}
