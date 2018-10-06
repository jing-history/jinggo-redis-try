package wang.jinggo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangyj
 * @description
 * @create 2018-10-06 15:41
 **/
@Configuration
public class BeanConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
