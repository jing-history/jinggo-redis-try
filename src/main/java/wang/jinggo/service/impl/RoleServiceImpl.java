package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.RoleDao;
import wang.jinggo.domain.Role;
import wang.jinggo.service.RoleService;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 10:51
 **/
@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> findByDefaultRole(Boolean defaultRole) {
        return roleDao.findByDefaultRole(defaultRole);
    }

    @Override
    public XbootBaseDao<Role, String> getRepository() {
        return roleDao;
    }
}
