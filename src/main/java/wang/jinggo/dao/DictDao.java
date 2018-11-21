package wang.jinggo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.Dict;

import java.util.List;

/**
 * 字典数据处理层
 * @author wangyj
 * @description
 * @create 2018-11-21 11:18
 **/
public interface DictDao extends XbootBaseDao<Dict,String> {
    /**
     * 通过type获取
     * @param type
     * @return
     */
    List<Dict> findByType(String type);
    /**
     * 模糊搜索
     * @param key
     * @return
     */
    @Query(value = "select * from t_dict d where d.title like %:key% or d.type like %:key%", nativeQuery = true)
    List<Dict> findByTitleOrTypeLike(@Param("key") String key);
}
