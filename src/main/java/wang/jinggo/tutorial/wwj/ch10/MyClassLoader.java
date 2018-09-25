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
public class MyClassLoader extends ClassLoader {

    //定义默认class 存放路径
    private final static Path DEFAULT_CLASS_DIR = Paths.get("E:","classloader1");

    private final Path classDir;

    public MyClassLoader() {
        super();
        this.classDir = DEFAULT_CLASS_DIR;
    }

    public MyClassLoader(Path classDir) {
        super();
        this.classDir = classDir;
    }

    public MyClassLoader(ClassLoader parent, Path classDir) {
        super(parent);
        this.classDir = classDir;
    }

    //重写父类的findClass 方法，重点
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        //读取class 的二进制数据
        byte[] classBytes = this.readClassBytes(name);
        if(null == classBytes || classBytes.length == 0){
            throw new ClassNotFoundException("Can not load the class " + name);
        }
        return this.defineClass(name, classBytes, 0 ,classBytes.length);
    }

    //将class 文件读入内存
    private byte[] readClassBytes(String name) throws ClassNotFoundException {
        //将包名分隔符转换为文件路径分隔符
        String classPath = name.replace(".", "/");
        Path classFullPath = classDir.resolve(Paths.get(classPath + ".class"));
        if(!classFullPath.toFile().exists()){
            throw new ClassNotFoundException("The class " + name + " not found.");
        }
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            Files.copy(classFullPath, baos);
            return baos.toByteArray();
        }catch (IOException e){
            throw new ClassNotFoundException("load the class " + name + " not occur error.",e);
        }
    }

    @Override
    public String toString() {
        return "My ClassLoader";
    }
}
