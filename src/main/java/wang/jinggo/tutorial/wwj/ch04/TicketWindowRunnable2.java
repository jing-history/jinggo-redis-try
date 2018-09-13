package wang.jinggo.tutorial.wwj.ch04;

/**
 * @author wangyj
 * @description
 * @create 2018-09-13 15:48
 **/
public class TicketWindowRunnable2 implements Runnable {
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
        final TicketWindowRunnable2 task = new TicketWindowRunnable2();
        Thread t1 = new Thread(task,"1 号窗口 ");
        Thread t2 = new Thread(task,"2 号窗口 ");
        Thread t3 = new Thread(task,"3 号窗口 ");
        Thread t4 = new Thread(task,"4 号窗口 ");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
