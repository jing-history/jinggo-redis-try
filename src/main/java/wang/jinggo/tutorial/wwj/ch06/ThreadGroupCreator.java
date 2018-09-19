package wang.jinggo.tutorial.wwj.ch06;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 17:08
 **/
public class ThreadGroupCreator {

    public static void main(String[] args) {
        //获取当前线程的group
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
        //定义一个新的group
        ThreadGroup group1 = new ThreadGroup("Group1");

        System.out.println(group1.getParent() == currentGroup);

        ThreadGroup group2 = new ThreadGroup(group1,"Group2");

        System.out.println(group2.getParent() == group1);
    }
}
