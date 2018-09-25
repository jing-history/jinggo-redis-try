package wang.jinggo.tutorial.wwj.ch10;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器必须是ClassLoader 的直接或者间接子类
 * @author wangyj
 * @description
 * @create 2018-09-25 16:39
 **/
public class BrokerDelegateClassLoader extends ClassLoader {

    //定义默认class 存放路径
    private final static Path DEFAULT_CLASS_DIR = Paths.get("E:","classloader1");

    private final Path classDir;

    public BrokerDelegateClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public BrokerDelegateClassLoader(Path classDir) {
        super();
        this.classDir = classDir;
    }

    public BrokerDelegateClassLoader(ClassLoader parent, Path classDir) {
        super(parent);
        this.classDir = classDir;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {

        //1
        synchronized (getClassLoadingLock(name)){
            //2.
            Class<?> klass = findLoadedClass(name);
            //3.
            if(klass == null){
                //4.
                if(name.startsWith("java.") || name.startsWith("javax")){
                    try {
                        klass = getSystemClassLoader().loadClass(name);
                    }catch (Exception e){

                    }
                }else {
                    //5.
                    try {
                        klass = this.findClass(name);
                    }catch (ClassNotFoundException e){

                    }
                    //6.
                    if(klass == null){
                        if(getParent() != null){
                            klass = getParent().loadClass(name);
                        }else {
                            klass = getSystemClassLoader().loadClass(name);
                        }
                    }
                }
            }
            //7.
            if(null == klass){
                throw new ClassNotFoundException("The class " + name +" not found.");
            }
            if(resolve){
                resolveClass(klass);
            }
            return klass;
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
