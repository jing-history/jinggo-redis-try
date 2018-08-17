package wang.jinggo.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 需要缓存到redis实体类
 * Created by gz12 on 2018-08-17.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RedisCache {
    public boolean need() default true;
}
