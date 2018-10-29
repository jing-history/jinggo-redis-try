package wang.jinggo.tutorial.wwj.ch17;

/**
 * @author wangyj
 * @description
 * @create 2018-10-29 10:08
 **/
public class ReadWriteLockIml implements ReadWriteLock {

    public ReadWriteLockIml() {

    }

    public ReadWriteLockIml(boolean preferWriter) {
    }

    @Override
    public Lock readLock() {
        return null;
    }

    @Override
    public Lock writeLock() {
        return null;
    }

    @Override
    public int getWritingWriters() {
        return 0;
    }

    @Override
    public int getReadingReaders() {
        return 0;
    }
}
