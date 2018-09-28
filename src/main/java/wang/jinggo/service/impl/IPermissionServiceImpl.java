package wang.jinggo.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.jinggo.dao.mapper.PermissionMapper;
import wang.jinggo.domain.Permission;
import wang.jinggo.service.mybatis.IPermissionService;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-28 10:21
 **/
@Service
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findByUserId(String userId) {
        return permissionMapper.findByUserId(userId);
    }
}
