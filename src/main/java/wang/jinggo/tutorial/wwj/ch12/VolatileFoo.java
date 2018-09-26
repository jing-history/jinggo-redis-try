package wang.jinggo.tutorial.wwj.ch12;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-26 16:03
 **/
public class VolatileFoo {

    // init_value 最大值
    final static int MAX = 5;
    // init_value 初始值
    static volatile int init_value = 0;

    //启动一个Reader 线程，当发现localValue 和init_value 不同时，则输出init_value 修改的信息
    public static void main(String[] args) {
        new Thread(() ->{
            int localValue = init_value;
            while (localValue < MAX){
                if(init_value != localValue){
                    System.out.printf("The init_value is updated to [%d]\n", init_value);

                    //对localValue 进行重新赋值
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        //启动一个Updater 线程,主要用于对init_value的修改，当localValue>=5 的时候则退出生命周期
        new Thread(() ->{
            int localValue = init_value;
            while (localValue < MAX){
                //修改init_value
                System.out.printf("The init_value will be changed to [%d]\n", ++localValue);

                init_value = localValue;
                try {
                    //短暂的休眠，目的是为了是Reader 线程能够来得及输出变化内容
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
