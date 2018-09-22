package wang.jinggo.tutorial.wwj.ch07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-21 11:20
 **/
public class PreventDuplicated {

    private final static String LOCK_PATH = "/";
    private final static String LOCK_FILE = ".lock";
    private final static String PERMSSIONS = "rw--------";

    public static void main(String[] args) throws IOException {
        //1.注入Hook线程，在程序退出时候删除lock文件
        Runtime.getRuntime().addShutdownHook(new Thread(() ->{
            System.out.println("The program received kill SIGNAL.");
            getLockFile().toFile().delete();
        }));

        //2. 检查是否存在.lock 文件
        checkRunning();

        // 简单模拟当前程序正在运行
        for (; ;){
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                System.out.println("program is runnning");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void checkRunning() throws IOException {
        Path path = getLockFile();
        if(path.toFile().exists())
            throw new RuntimeException("The program already running.");
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString(PERMSSIONS);
        Files.createFile(path, PosixFilePermissions.asFileAttribute(perms));
    }

    public static Path getLockFile() {
        return Paths.get(LOCK_PATH,LOCK_FILE);
    }
}
