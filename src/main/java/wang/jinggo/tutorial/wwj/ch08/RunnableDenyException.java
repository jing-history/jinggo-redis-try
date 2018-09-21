package wang.jinggo.tutorial.wwj.ch08;

/**
 * @author wangyj
 * @description
 * @create 2018-09-21 14:52
 **/
public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message) {
        super(message);
    }
}
