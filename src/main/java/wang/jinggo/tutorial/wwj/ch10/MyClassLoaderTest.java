package wang.jinggo.tutorial.wwj.ch10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 17:21
 **/
public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader classLoader = new MyClassLoader();
        Class<?> aClass = classLoader.loadClass("wang.jinggo.tutorial.wwj.ch10.HelloWorld");

        System.out.println(aClass.getClassLoader());
        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method method = aClass.getMethod("welcome");
        String result = (String) method.invoke(helloWorld);
        System.out.println("Result: " + result);
    }
}
