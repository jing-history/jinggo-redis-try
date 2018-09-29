package wang.jinggo.tutorial.wwj.ch14;

/**
 * 单例 使用枚举实现
 * issues 枚举类型本身是final 的，不允许被继承
 * 增加懒加载的特性
 * @author wangyj
 * @description
 * @create 2018-09-28 16:34
 **/
public class Singleton8 {

    //实例变量
    private byte[] data = new byte[1024];

    //私有构造函数，不允许外部new
    private Singleton8() {

    }

    //使用美军充当holder
    private enum EnumHolder{
        INSTANCE;
        private Singleton8 instance;

        EnumHolder(){
            this.instance = new Singleton8();
        }

        private Singleton8 getSingleton(){
            return instance;
        }
    }

    public static Singleton8 getInsatance()
    {
        return EnumHolder.INSTANCE.getSingleton();
    }
}
