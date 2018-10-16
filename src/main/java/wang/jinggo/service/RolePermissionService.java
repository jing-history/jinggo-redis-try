package wang.jinggo.service;

import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.RolePermission;

import java.util.List;

/**
 * 角色权限接口
 */
public interface RolePermissionService extends XbootBaseService<RolePermission,String> {

    /**
     * 通过permissionId获取
     * @param permissionId
     * @return
     */
    List<RolePermission> findByPermissionId(String permissionId);

    /**
     * 通过roleId删除
     * @param roleId
     */
    void deleteByRoleId(String roleId);
}

