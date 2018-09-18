package wang.jinggo.annation;

import java.lang.annotation.*;

/**
 * 限流注解
 * Created by gz12 on 2018-09-18.
 */
@Target(ElementType.METHOD)//作用于方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    int limit() default 5;
    int timeout() default 1000;
}
