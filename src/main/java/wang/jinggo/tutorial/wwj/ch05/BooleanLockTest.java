package wang.jinggo.tutorial.wwj.ch05;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

/**
 * @author wangyj
 * @description
 * @create 2018-09-18 15:42
 **/
public class BooleanLockTest {

    //定义BooleanLock
    private final Lock lock = new BooleanLock();

    public void sycMethod(){

        try {
            //加锁
            lock.lock();

            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + " get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BooleanLockTest blt = new BooleanLockTest();
        IntStream.range(0,10)
                .mapToObj(i -> new Thread(blt::sycMethod))
                .forEach(Thread::start);
    }
}
