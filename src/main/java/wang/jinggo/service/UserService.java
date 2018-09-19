package wang.jinggo.service;

import org.springframework.cache.annotation.CacheConfig;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.User;

/**
 * 用户接口
 * @author wangyj
 * @description
 * @create 2018-09-19 14:49
 **/
@CacheConfig(cacheNames = "user")
public interface UserService extends XbootBaseService<User,String> {

}
