package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.Permission;

import java.util.List;

/**
 * 权限数据处理层
 * @author wangyj
 * @description
 * @create 2018-09-25 14:36
 **/
public interface PermissionDao extends XbootBaseDao<Permission,String> {

    /**
     * 通过类型和状态获取
     * @param type
     * @param status
     * @return
     */
    List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status);
}
