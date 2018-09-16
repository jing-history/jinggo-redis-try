package wang.jinggo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * srping boot 2.x 一书教程
 * 1.切面的使用
 * execution(public * *(..)) : 所有public 方法，后面的星号代表类路径和方法名；
 * execution(* set*(..)) : 所有set 开头的方法；
 * execution(public set*(..)) : 所有set 开头的 public 方法；
 * execution(public wang.jinggo.service.* set*(..)) : 所有set 开头的pubic方法，
 * 且位于wang.jinggo 包下；
 * target(wang.jinggo.service.BaseService) : 所有实现了BaseService 接口的类的方法
 * target(org.springframe.transaction.annotation.Transactional) : 所有用
 * @Transactional 注解的方法；
 * @within(org.springframework.stereotype.Controller) ： 类型声明了@Controller 所有方法。
 */
@Configuration
@Aspect
public class AopConfig {

    private final static Logger LOG =  LoggerFactory.getLogger(DruidConfiguration.class);

    //Spring controller 的方法都被监控
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object simpleAop(final ProceedingJoinPoint point) throws Throwable {

        try {
            Object[] args = point.getArgs();
            LOG.info("args: " + Arrays.asList(args));
            //调用原来的方法
            Object o = point.proceed();
            LOG.info("return: " + o);
            return o;
        } catch (Throwable throwable) {
            throw throwable;
        }
    }
}
