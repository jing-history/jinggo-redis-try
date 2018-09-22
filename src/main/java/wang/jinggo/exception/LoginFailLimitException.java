package wang.jinggo.exception;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @author wangyj
 * @description
 * @create 2018-09-20 10:44
 **/
@Data
public class LoginFailLimitException extends InternalAuthenticationServiceException {

    private String msg;

    public LoginFailLimitException(String msg){
        super(msg);
        this.msg = msg;
    }

    public LoginFailLimitException(String msg, Throwable t) {
        super(msg, t);
        this.msg = msg;
    }
}
