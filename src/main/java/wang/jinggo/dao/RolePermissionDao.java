package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.RolePermission;

import java.util.List;

public interface RolePermissionDao extends XbootBaseDao<RolePermission,String>  {

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
