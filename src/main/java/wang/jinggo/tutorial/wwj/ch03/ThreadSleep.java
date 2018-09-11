package wang.jinggo.tutorial.wwj.ch03;

/**
 * @author wangyj
 * @description
 * @create 2018-09-11 17:21
 **/
public class ThreadSleep {
    public static void main(String[] args) {
        new Thread(() ->{
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms", (endTime - startTime)));
        }).start();

        long startTime = System.currentTimeMillis();
        sleep(3_000L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main Thread total spend %d ms", (endTime - startTime)));
    }

    private static void sleep(long ms) {
        try {
            //这个只会导致当前线程进入指定时间休眠
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
