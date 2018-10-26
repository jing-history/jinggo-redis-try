package wang.jinggo.config.interceptor;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 跨域过滤器 注意：首先要先跨域才能到spring Security 校验
 * @author wangyj
 * @description
 * @create 2018-10-26 19:56
 **/
@WebFilter(filterName="CorsFilter",urlPatterns="/**")
public class CorsFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type,accesstoken,Accept,X-Requested-With");
        //是否允许浏览器携带用户身份信息（cookie）
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        if (request.getMethod().equals( "OPTIONS" )) {
            response.setStatus( 200 );
            return;
        }

        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
