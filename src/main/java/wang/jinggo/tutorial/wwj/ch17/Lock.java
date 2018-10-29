package wang.jinggo.tutorial.wwj.ch17;

/**
 * @author wangyj
 * @description
 * @create 2018-10-29 9:54
 **/
public interface Lock {
    //获取显示锁，没有获得锁的线程将被阻塞
    void lock() throws InterruptedException;
    //释放获取的锁
    void unlock();
}
