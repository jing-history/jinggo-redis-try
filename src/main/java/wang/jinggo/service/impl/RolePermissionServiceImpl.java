package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.jinggo.dao.RolePermissionDao;
import wang.jinggo.domain.RolePermission;
import wang.jinggo.service.RolePermissionService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public RolePermissionDao getRepository() {
        return rolePermissionDao;
    }

    @Override
    public List<RolePermission> findByPermissionId(String permissionId) {

        return rolePermissionDao.findByPermissionId(permissionId);
    }

    @Override
    public void deleteByRoleId(String roleId) {

        rolePermissionDao.deleteByRoleId(roleId);
    }
}
