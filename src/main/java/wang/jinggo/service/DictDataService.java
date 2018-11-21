package wang.jinggo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.DictData;

import java.util.List;

/**
 * 字典数据接口
 * Created by gz12 on 2018-11-21.
 */
public interface DictDataService extends XbootBaseService<DictData,String>  {

    /**
     * 多条件获取
     * @param dictData
     * @param pageable
     * @return
     */
    Page<DictData> findByCondition(DictData dictData, Pageable pageable);
    /**
     * 通过dictId获取启用字典 已排序
     * @param dictId
     * @return
     */
    List<DictData> findByDictId(String dictId);
    /**
     * 通过dictId删除
     * @param dictId
     */
    void deleteByDictId(String dictId);
}
