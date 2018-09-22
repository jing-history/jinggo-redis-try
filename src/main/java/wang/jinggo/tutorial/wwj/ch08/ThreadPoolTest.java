package wang.jinggo.tutorial.wwj.ch08;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-22 11:06
 **/
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        //定义线程池，初始化线程数为2 ，核心线程数为4，最大线程数为6，任务队列最多允许1000 个任务
        final ThreadPool threadPool = new BasicThreadPool(2,6,4,100);
        //定义20 个任务并且提交到线程池
        for (int i = 0; i < 20 ; i++)
            threadPool.execute(() ->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + " is running and done.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

        for (; ; ){
            //不断输出线程池的信息
            System.out.println("getActiveCount: " + threadPool.getActiveCount());
            System.out.println("getQueueSize: " + threadPool.getQueueSize());
            System.out.println("getCoreSize: " + threadPool.getCoreSize());
            System.out.println("getMaxSize: " + threadPool.getMaxSize());
            TimeUnit.SECONDS.sleep(5);
        }

        //销毁功能
        //TimeUnit.SECONDS.sleep(12);
        //threadPool.shutdown();

        //使用main 线程join，方便通过工具观察线程堆栈信息
       // Thread.currentThread().join();
    }
}
