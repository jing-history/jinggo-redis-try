package wang.jinggo.tutorial.wwj.ch03;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 9:27
 **/
public class ThreadPriority {
    public static void main(String[] args) {
        Thread t1 = new Thread(() ->{
            while (true){
                System.out.println("t1");
            }
        });
        t1.setPriority(3);
        Thread t2 = new Thread(() ->{
            while (true){
                System.out.println("t2");
            }
        });
        t2.setPriority(10);
        t1.start();
        t2.start();
    }
}
