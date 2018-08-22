package wang.jinggo.service;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-08-22 16:25
 **/
public interface BaseService<T> {

    /* 查询所有 */
    public List<T> findAll();

    /* 删除 */
    void delete(String id);

    /* 更新 */
    void update(T t);

    /**/
    void insert(T t);
}
