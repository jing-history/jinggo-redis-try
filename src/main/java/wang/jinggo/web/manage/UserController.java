package wang.jinggo.web.manage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.User;
import wang.jinggo.service.UserService;
import wang.jinggo.util.ResultUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author wangyj
 * @description
 * @create 2018-09-27 17:35
 **/
@Slf4j
@RestController
@Api(description = "用户接口")
@RequestMapping("/xboot/user")
@CacheConfig(cacheNames = "user")
@Transactional
public class UserController {

    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ApiOperation(value = "获取当前登录用户接口")
    public Result<User> getUserInfo(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User u = userService.findByUsername(user.getUsername());
        // 清除持久上下文环境 避免后面语句导致持久化
        entityManager.clear();
        u.setPassword(null);
        return new ResultUtil<User>().setData(u);
    }
}
