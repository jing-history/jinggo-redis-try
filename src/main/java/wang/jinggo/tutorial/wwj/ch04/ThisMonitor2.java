package wang.jinggo.tutorial.wwj.ch04;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-13 16:53
 **/
public class ThisMonitor2 {
    public synchronized void method1(){
        System.out.println(Thread.currentThread().getName() + " enter to method1.");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void method2(){
        synchronized (this){
            System.out.println(Thread.currentThread().getName() + " enter to method2.");
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThisMonitor2 thisMonitor =new ThisMonitor2();
        new Thread(thisMonitor::method1, "T1").start();
        new Thread(thisMonitor::method2, "T2").start();
    }
}
