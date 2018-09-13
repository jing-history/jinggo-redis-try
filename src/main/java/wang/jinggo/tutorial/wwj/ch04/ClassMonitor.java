package wang.jinggo.tutorial.wwj.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-13 17:03
 **/
public class ClassMonitor {
    public static synchronized void method1(){
        System.out.println(Thread.currentThread().getName() + " enter to method1.");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static synchronized void method2(){
        System.out.println(Thread.currentThread().getName() + " enter to method2.");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(ClassMonitor::method1, "T1").start();
        new Thread(ClassMonitor::method2, "T2").start();
    }
}
