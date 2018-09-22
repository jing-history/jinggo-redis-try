package wang.jinggo.annation;

import java.lang.annotation.*;

/**
 * 系统日志自定义注解
 * Created by gz12 on 2018-09-22.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})//作用于参数或方法上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String description() default "";
}
