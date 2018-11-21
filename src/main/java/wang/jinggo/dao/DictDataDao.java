package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.DictData;

import java.util.List;

/**
 * 字典数据数据处理层
 * Created by gz12 on 2018-11-21.
 */
public interface DictDataDao extends XbootBaseDao<DictData,String> {

    /**
     * 通过dictId和状态获取
     * @param dictId
     * @param status
     * @return
     */
    List<DictData> findByDictIdAndStatusOrderBySortOrder(String dictId, Integer status);
    /**
     * 通过dictId删除
     * @param dictId
     */
    void deleteByDictId(String dictId);
}
