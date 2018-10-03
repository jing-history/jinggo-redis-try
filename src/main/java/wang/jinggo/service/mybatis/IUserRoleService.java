package wang.jinggo.service.mybatis;

import com.baomidou.mybatisplus.service.IService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import wang.jinggo.domain.Role;
import wang.jinggo.domain.UserRole;

import java.util.List;

@CacheConfig(cacheNames = "userRole")
public interface IUserRoleService  extends IService<UserRole>  {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    @Cacheable(key = "#userId")
    List<Role> findByUserId(@Param("userId") String userId);
}
