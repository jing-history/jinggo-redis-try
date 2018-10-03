package wang.jinggo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.jinggo.dao.mapper.UserRoleMapper;
import wang.jinggo.domain.Role;
import wang.jinggo.domain.UserRole;
import wang.jinggo.service.mybatis.IUserRoleService;

import java.util.List;

@Service
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Role> findByUserId(String userId) {

        return userRoleMapper.findByUserId(userId);
    }
}
