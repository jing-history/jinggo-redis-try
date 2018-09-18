package wang.jinggo.tutorial.wwj.ch05;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-17 14:02
 **/
public class EventClient {

    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for(; ; ){
                eventQueue.offer(new EventQueue.Event());
            }
        },"Producer").start();

        new Thread(() -> {
            for(; ; ){
                eventQueue.take();

                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Consumer").start();
    }
}
