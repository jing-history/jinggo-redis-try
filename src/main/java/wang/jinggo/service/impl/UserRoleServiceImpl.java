package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.UserRoleDao;
import wang.jinggo.domain.UserRole;
import wang.jinggo.service.UserRoleService;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-10-05 11:01
 **/
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<UserRole> findByRoleId(String roleId) {
        return userRoleDao.findByRoleId(roleId);
    }

    @Override
    public void deleteByUserId(String userId) {
        userRoleDao.deleteByUserId(userId);
    }

    @Override
    public UserRoleDao getRepository() {
        return userRoleDao;
    }
}
