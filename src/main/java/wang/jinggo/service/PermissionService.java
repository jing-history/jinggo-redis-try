package wang.jinggo.service;

import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.Permission;

import java.util.List;

/**
 * 权限接口
 * @author wangyj
 * @description
 * @create 2018-09-25 14:21
 **/
public interface PermissionService extends XbootBaseService<Permission, String> {

    /**;
     * 通过类型和状态获取
     * @param type
     * @param status
     * @return
     */
    List<Permission> findByTypeAndStatusOrderBySortOrder(Integer type, Integer status);
}
