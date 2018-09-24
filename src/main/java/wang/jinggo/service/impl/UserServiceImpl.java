package wang.jinggo.service.impl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.dao.UserDao;
import wang.jinggo.domain.Department;
import wang.jinggo.domain.User;
import wang.jinggo.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 用户接口实现
 * @author wangyj
 * @description
 * @create 2018-09-20 10:48
 **/
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDao getRepository() {
        return userDao;
    }

    @Override
    public User findByUsername(String username) {

        List<User> list=userDao.findByUsernameAndStatus(username, CommonConstant.USER_STATUS_NORMAL);
        if(list!=null&&list.size()>0){
            User user = list.get(0);
            // 关联部门
            /*if(StrUtil.isNotBlank(user.getDepartmentId())){
                Department department = departmentDao.getOne(user.getDepartmentId());
                user.setDepartmentTitle(department.getTitle());
            }*/

        }
        return null;
    }
}
