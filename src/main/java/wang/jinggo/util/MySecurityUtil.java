package wang.jinggo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import wang.jinggo.domain.User;
import wang.jinggo.service.UserService;

/**
 * @author wangyj
 * @description
 * @create 2018-11-26 18:41
 **/
public class MySecurityUtil {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     * @return
     */
    public User getCurrUser(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByUsername(user.getUsername());
    }
}
