package wang.jinggo.tutorial.wwj.ch07;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-21 10:45
 **/
public class ThreadHook {
    public static void main(String[] args) {
        //为应用程序注入钩子线程
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                try {
                    System.out.println("The hook thread 1 is runnung.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 1 will exit.");
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {

                try {
                    System.out.println("The hook thread 2 is runnung.");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The hook thread 2 will exit.");
            }
        });
        System.out.println("The program will is stoppting.");
    }
}
