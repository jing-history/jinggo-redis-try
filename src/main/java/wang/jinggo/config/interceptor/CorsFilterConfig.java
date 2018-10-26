package wang.jinggo.config.interceptor;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangyj
 * @description
 * @create 2018-10-26 19:56
 **/
@Configuration
@EnableAutoConfiguration
public class CorsFilterConfig extends SpringBootServletInitializer {

    /*FilterRegistrationBean 用来配置urlpattern 来确定哪些路径触发filter */
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(AuthFilter());
        registration.addUrlPatterns("/**");
        registration.setOrder(1);
        return registration;
    }

    @Bean()
    public CorsFilter AuthFilter() {
        return new CorsFilter();
    }
}
