package wang.jinggo.tutorial.wwj.ch14;

/**
 * 单例 饿汉式
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
//final 不允许被继承
public final class Singleton {

    //实例变量
    private byte[] data = new byte[1024];

    //在定义实例对象的时候直接初始化
    private static Singleton insatance = new Singleton();

    //私有构造函数，不允许外部new
    private Singleton() {
    }

    public static Singleton getInsatance(){
        return insatance;
    }
}
