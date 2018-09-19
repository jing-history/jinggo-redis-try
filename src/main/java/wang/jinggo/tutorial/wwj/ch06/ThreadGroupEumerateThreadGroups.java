package wang.jinggo.tutorial.wwj.ch06;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 17:13
 **/
public class ThreadGroupEumerateThreadGroups {

    public static void main(String[] args) throws InterruptedException {
        //1. 创建一个ThreadGroup
        ThreadGroup myGroup1 = new ThreadGroup("MyGroup1");
        ThreadGroup myGroup2 = new ThreadGroup(myGroup1,"MyGroup2");

        TimeUnit.SECONDS.sleep(2);
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup[] list = new ThreadGroup[mainGroup.activeCount()];
        int recourseSize = mainGroup.enumerate(list);
        System.out.println(recourseSize);

        recourseSize = mainGroup.enumerate(list, false);
        System.out.println(recourseSize);
    }
}
