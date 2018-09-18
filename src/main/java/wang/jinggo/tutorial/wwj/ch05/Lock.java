package wang.jinggo.tutorial.wwj.ch05;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author wangyj
 * @description
 * @create 2018-09-18 15:17
 **/
public interface Lock {
    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();
}
