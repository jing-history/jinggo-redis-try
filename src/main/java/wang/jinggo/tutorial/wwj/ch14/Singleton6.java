package wang.jinggo.tutorial.wwj.ch14;

/**
 * 单例 Holder 方式
 * issues Holder 方式的单例是最好的设计之一，使用比较广泛的
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
//final 不允许被继承
public final class Singleton6 {

    //实例变量
    private byte[] data = new byte[1024];

    //私有构造函数，不允许外部new
    private Singleton6() {
    }

    private static class Holder{
        private static Singleton6 instance = new Singleton6();
    }

    public static Singleton6 getInsatance(){
        return Holder.instance;
    }
}
