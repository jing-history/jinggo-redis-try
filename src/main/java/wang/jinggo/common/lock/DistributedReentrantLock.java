package wang.jinggo.common.lock;

import java.util.concurrent.TimeUnit;

/**
 * Created by gz12 on 2018-09-18.
 */
public interface DistributedReentrantLock {

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException;

    public void unlock();
}
