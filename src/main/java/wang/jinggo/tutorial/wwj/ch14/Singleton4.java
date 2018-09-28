package wang.jinggo.tutorial.wwj.ch14;

import java.net.Socket;
import java.sql.Connection;

/**
 * 单例 Double-Check
 * issues 下面这种方式 有可能引起空指针异常
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
//final 不允许被继承
public final class Singleton4 {

    //实例变量
    private byte[] data = new byte[1024];

    //在定义实例对象的时候直接初始化
    private static Singleton4 insatance = null;

    //私有构造函数，不允许外部new
    private Singleton4() {
    }

    public static Singleton4 getInsatance(){
        //当insatance 为null 时，进入同步代码块，同时该判断避免 了每次都需要进入同步代码块，可以提高效率
        if(null == insatance){
            //只有一个线程能够获取Singleton4.class 关联的monitor
            synchronized (Singleton4.class){
                if(null == insatance){
                    insatance = new Singleton4();
                }
            }
        }
        return insatance;
    }
}
