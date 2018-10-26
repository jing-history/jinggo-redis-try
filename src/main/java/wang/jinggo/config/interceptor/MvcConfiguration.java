package wang.jinggo.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;
import wang.jinggo.common.limit.RedisRaterLimiter;
import wang.jinggo.config.IgnoredUrlsProperties;

import java.util.ArrayList;
import java.util.List;

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
        //配置拦截器的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
        ir.excludePathPatterns(ignoredUrlsProperties.getUrls());
    }

    //跨域访问配置
     /*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //添加映射路径
                .allowedOrigins("*")    //放行哪些原始域
                .allowCredentials(true)  //是否发送Cookie信息
                .allowedMethods("GET", "POST", "DELETE", "PUT") //放行哪些原始域(请求方式)
                .allowedHeaders("*")    //放行哪些原始域(头部信息)
                .maxAge(3600);
    }


    下面这种也是全局配置的方法
    // 请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）

    参考 https://blog.csdn.net/c5113620/article/details/79132968

    private CorsConfiguration addcorsConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> list = new ArrayList<>();
        list.add("*");
        corsConfiguration.setAllowedOrigins(list);

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", addcorsConfig());
        return new CorsFilter(source);
    }*/

    //格式化
    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    //UrI 到视图的映射

}
