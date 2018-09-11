package wang.jinggo.tutorial.wwj.ch02;

/**
 * @author wangyj
 * @description
 * @create 2018-09-11 17:04
 **/
public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000);
        System.out.println("Main thread finished lifecyle.");
    }
}
