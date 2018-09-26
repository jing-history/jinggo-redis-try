package wang.jinggo.tutorial.wwj.ch10;

/**
 * @author wangyj
 * @description
 * @create 2018-09-26 15:03
 **/
public class NameSpace {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = NameSpace.class.getClassLoader();
        Class<?> aclass = classLoader.loadClass("wang.jinggo.tutorial.wwj.ch10.MyClassLoaderTest");
        Class<?> bclass = classLoader.loadClass("wang.jinggo.tutorial.wwj.ch10.MyClassLoaderTest");
        System.out.println(aclass.hashCode());
        System.out.println(bclass.hashCode());
        System.out.println(aclass == bclass);
    }
}
