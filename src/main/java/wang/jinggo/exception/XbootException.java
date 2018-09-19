package wang.jinggo.exception;

import lombok.Data;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 14:23
 **/
@Data
public class XbootException extends RuntimeException{

    private String msg;

    public XbootException(String msg){
        super(msg);
        this.msg = msg;
    }
}
