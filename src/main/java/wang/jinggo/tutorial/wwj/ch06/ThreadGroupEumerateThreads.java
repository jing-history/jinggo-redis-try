package wang.jinggo.tutorial.wwj.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 17:13
 **/
public class ThreadGroupEumerateThreads {

    public static void main(String[] args) throws InterruptedException {
        //1. 创建一个ThreadGroup
        ThreadGroup myGroup = new ThreadGroup("MyGroup");
        //2. 创建线程传入 threadGroup
        Thread thread = new Thread(myGroup, () -> {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "MyThread");
        thread.start();

        TimeUnit.SECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        Thread[] list = new Thread[mainGroup.activeCount()];
        int recourseSize = mainGroup.enumerate(list);
        System.out.println(recourseSize);

        recourseSize = mainGroup.enumerate(list, false);
        System.out.println(recourseSize);
    }
}
