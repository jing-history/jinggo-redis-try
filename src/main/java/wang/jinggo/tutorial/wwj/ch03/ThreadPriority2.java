package wang.jinggo.tutorial.wwj.ch03;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 9:30
 **/
public class ThreadPriority2 {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("test");
        group.setMaxPriority(7);
        Thread thread = new Thread(group, "test-thread");
        thread.setPriority(10);
        System.out.println(thread.getPriority());
    }
}
