package wang.jinggo.tutorial.wwj.ch14;

/**
 * 单例 懒汉式
 * issues 下面这种方式 instance 不能保证唯一性
 * 多个线程会实例化多个instance
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
//final 不允许被继承
public final class Singleton2 {

    //实例变量
    private byte[] data = new byte[1024];

    //在定义实例对象的时候直接初始化
    private static Singleton2 insatance = null;

    //私有构造函数，不允许外部new
    private Singleton2() {
    }

    public static Singleton2 getInsatance(){
        if(null == insatance)
            insatance = new Singleton2();
        return insatance;
    }
}
