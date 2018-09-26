package wang.jinggo.tutorial.wwj.ch09;

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 15:57
 **/
public class Singleton {

    //1.
    private static int x = 0;

    private static int y;

    //2.
    private static Singleton singleton = new Singleton();

    private Singleton(){
        x++;
        y++;
    }

    public static Singleton getSingleton(){
        return singleton;
    }

    public static void main(String[] args) {
        Singleton singleton = Singleton.getSingleton();
        System.out.println(singleton.x);
        System.out.println(singleton.y);
    }
}
