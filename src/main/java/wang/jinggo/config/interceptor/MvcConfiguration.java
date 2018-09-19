package wang.jinggo.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
import wang.jinggo.common.limit.RedisRaterLimiter;
import wang.jinggo.config.IgnoredUrlsProperties;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private IgnoredUrlsProperties ignoredUrlsProperties;
    @Autowired
    private limitRaterInterceptor limitRaterInterceptor;
    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(limitRaterInterceptor);
    }

    //跨域访问配置
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    //格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    //UrI 到视图的映射

}
