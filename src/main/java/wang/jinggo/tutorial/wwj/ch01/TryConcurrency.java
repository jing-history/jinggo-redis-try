package wang.jinggo.tutorial.wwj.ch01;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-10 17:58
 **/
public class TryConcurrency {

    public static void main(String[] args) {
        browseNews();
        enjoyMusic();
    }

    private static void enjoyMusic() {
        for (; ; ){
            System.out.println("Uh-huh, the nice music.");
            sleep(1);
        }
    }

    private static void browseNews() {
        for (; ; ){
            System.out.println("Uh-huh, the good news.");
            sleep(1);
        }
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
