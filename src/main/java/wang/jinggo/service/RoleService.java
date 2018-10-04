package wang.jinggo.service;

import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.Role;

import java.util.List;

/**
 * 角色接口
 * @author wangyj
 * @description
 * @create 2018-10-04 10:49
 **/
public interface RoleService  extends XbootBaseService<Role,String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
