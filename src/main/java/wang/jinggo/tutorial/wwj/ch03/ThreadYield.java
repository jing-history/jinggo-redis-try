package wang.jinggo.tutorial.wwj.ch03;

import java.util.stream.IntStream;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 9:09
 **/
public class ThreadYield {
    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYield::create)
                .forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() ->{
            if(index == 0)
                Thread.yield();
            System.out.println(index);
        });
    }

}
