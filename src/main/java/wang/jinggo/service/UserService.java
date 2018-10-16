package wang.jinggo.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.User;
import wang.jinggo.pojo.SearchVo;

import java.util.List;

/**
 * 用户接口
 * @author wangyj
 * @description
 * @create 2018-09-19 14:49
 **/
@CacheConfig(cacheNames = "user")
public interface UserService extends XbootBaseService<User,String> {
    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    @Cacheable(key = "#username")
    User findByUsername(String username);

    /**
     * 多条件分页获取用户
     * @param user
     * @param searchVo
     * @param pageable
     * @return
     */
    Page<User> findByCondition(User user, SearchVo searchVo, Pageable pageable);

    /**
     * 通过部门id获取
     * @param departmentId
     * @return
     */
    List<User> findByDepartmentId(String departmentId);

}
