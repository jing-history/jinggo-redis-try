package wang.jinggo.tutorial.wwj.ch17;

/**
 * @author wangyj
 * @description
 * @create 2018-10-29 9:56
 **/
public interface ReadWriteLock {

    //创建reader 锁
    Lock readLock();

    //创建write 锁
    Lock writeLock();

    //获取当前有多少线程正在执行写操作
    int getWritingWriters();

    //获取当前有多少线程正在等待获取写入 reader 锁
    int getReadingReaders();

    //工厂方法，创建ReadWriteLock
    static ReadWriteLock readWriteLock(){
        return new ReadWriteLockIml();
    }

    //工厂方法，创建ReadWriteLock，并且传入preferWroter
    static ReadWriteLock readWriteLock(boolean preferWriter){
        return new ReadWriteLockIml(preferWriter);
    }
}
