package wang.jinggo.tutorial.wwj.ch14;

/**
 * 单例 使用枚举实现
 * issues 枚举类型本身是final 的，不允许被继承
 * 但是不支持懒加载特性
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
public enum Singleton7 {

    INSTANCE;

    //实例变量
    private byte[] data = new byte[1024];

    //私有构造函数，不允许外部new
    Singleton7() {
        System.out.println("Instance will be init immediately.");
    }

    public static void method(){
        //调用该方法则会主动使用Singleton，INSTANCE将会呗实例化
    }

    public static Singleton7 getInsatance(){
        return INSTANCE;
    }
}
