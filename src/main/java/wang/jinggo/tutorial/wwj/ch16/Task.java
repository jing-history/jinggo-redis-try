package wang.jinggo.tutorial.wwj.ch16;

/**
 * @author wangyj
 * @description
 * @create 2018-09-29 10:40
 **/
@FunctionalInterface
public interface Task<T> {

    //任务执行接口，该接口允许有返回值
    T call();
}
