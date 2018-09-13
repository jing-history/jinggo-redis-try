package wang.jinggo.tutorial.wwj.ch04;

/**
 * @author wangyj
 * @description
 * @create 2018-09-13 15:48
 **/
public class TicketWindowRunnable3 implements Runnable {
    private int index = 1;
    private final static int MAX = 500;

    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX){
            while (index <= MAX){
                System.out.println(Thread.currentThread() + " 的号码是：" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(TicketWindowRunnable3::new).start();
        }
    }
}
