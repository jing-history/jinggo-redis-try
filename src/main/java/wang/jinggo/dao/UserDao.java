package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.User;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-20 10:49
 **/
public interface UserDao extends XbootBaseDao<User,String> {

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

    /**
     * 通过部门id获取
     * @param departmentId
     * @return
     */
    List<User> findByDepartmentId(String departmentId);
}
