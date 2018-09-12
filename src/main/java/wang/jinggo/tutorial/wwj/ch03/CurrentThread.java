package wang.jinggo.tutorial.wwj.ch03;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 9:38
 **/
public class CurrentThread {
    public static void main(String[] args) {
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("new Thread name: " + Thread.currentThread());
                System.out.println(Thread.currentThread() == this);
            }
        };
        thread.start();

        String name = Thread.currentThread().getName();
        System.out.println("main Thread name: " + Thread.currentThread());
        System.out.println("main".equals(name));
    }
}
