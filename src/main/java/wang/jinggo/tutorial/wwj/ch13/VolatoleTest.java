package wang.jinggo.tutorial.wwj.ch13;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangyj
 * @description
 * @create 2018-09-27 16:25
 **/
public class VolatoleTest {

    private static volatile int i = 0;
    private static final CountDownLatch latch = new CountDownLatch(10);

    private static void inc(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int j = 0; j < 10; j++) {
            new Thread(() -> {
                for (int k = 0; k < 1000; k++) {
                    inc();
                }
                //使计数器减1
                latch.countDown();
            }).start();
        }
        // 等待所有的线程完成工作
        latch.await();
        System.out.println(i);
    }
}
