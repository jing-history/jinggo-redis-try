package wang.jinggo.tutorial.wwj.ch03;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 16:34
 **/
public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start work");
                while (!isInterrupted()){
                    //working
                    System.out.println("isInterrupted...");
                }
                System.out.println("I will be exiting.");
            }
        };
        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdown.");
        t.interrupt();
    }
}
