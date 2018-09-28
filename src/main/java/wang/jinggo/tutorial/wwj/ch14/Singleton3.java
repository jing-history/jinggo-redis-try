package wang.jinggo.tutorial.wwj.ch14;

/**
 * 单例 懒汉式 + 同步实现单例模式
 * issues 下面这种方式 instance 能保证唯一性
 * 但是 synchronized 排他性导致 getInsatance 只能被一个线程所访问
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
//final 不允许被继承
public final class Singleton3 {

    //实例变量
    private byte[] data = new byte[1024];

    //在定义实例对象的时候直接初始化
    private static Singleton3 insatance = null;

    //私有构造函数，不允许外部new
    private Singleton3() {
    }

    // 向getInsatance 方法加入同步控制，每次只能有一个线程进入
    public static synchronized Singleton3 getInsatance(){
        if(null == insatance)
            insatance = new Singleton3();
        return insatance;
    }
}
