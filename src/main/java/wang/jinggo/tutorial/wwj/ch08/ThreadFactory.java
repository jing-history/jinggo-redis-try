package wang.jinggo.tutorial.wwj.ch08;

/**
 * 创建线程的工厂
 * @author wangyj
 * @description
 * @create 2018-09-21 14:35
 **/
@FunctionalInterface
public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
