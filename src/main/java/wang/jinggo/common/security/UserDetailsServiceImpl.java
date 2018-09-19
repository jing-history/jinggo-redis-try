package wang.jinggo.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import wang.jinggo.service.UserService;
import wang.jinggo.util.RedisCacheManager;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 14:46
 **/
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    RedisCacheManager redisCacheManager;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
