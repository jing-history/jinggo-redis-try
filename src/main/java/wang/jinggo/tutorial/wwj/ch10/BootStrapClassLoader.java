package wang.jinggo.tutorial.wwj.ch10;

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 16:32
 **/
public class BootStrapClassLoader {

    public static void main(String[] args) {
        System.out.println("Bootstrap: " + String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
