package wang.jinggo.tutorial.wwj.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 17:38
 **/
public class ThreadGroupDaemon {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup("Group1");

        new Thread(group1, () -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "g1").start();

        ThreadGroup group2 = new ThreadGroup("Group2");
        new Thread(group2, () -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }, "g2").start();

        group2.setDaemon(true);
        TimeUnit.SECONDS.sleep(3);

        System.out.println(group1.isDestroyed());
        System.out.println(group2.isDestroyed());
    }
}
