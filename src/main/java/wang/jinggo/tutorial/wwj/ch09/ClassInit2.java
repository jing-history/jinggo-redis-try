package wang.jinggo.tutorial.wwj.ch09;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 16:18
 **/
public class ClassInit2 {

    //父类中有静态变量value
    static class Parent{

        static {
            try {
                System.out.println("The classInit2 static code block will be invoke.");
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) {
            IntStream.range(0, 5)
                    .forEach(i -> new Thread(ClassInit2::new));
        }
    }
}


