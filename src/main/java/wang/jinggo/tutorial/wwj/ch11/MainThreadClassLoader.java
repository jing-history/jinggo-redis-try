package wang.jinggo.tutorial.wwj.ch11;

import static java.lang.Thread.currentThread;

/**
 * @author wangyj
 * @description
 * @create 2018-09-26 15:31
 **/
public class MainThreadClassLoader {

    public static void main(String[] args) {
        System.out.println(currentThread().getContextClassLoader());
    }
}
