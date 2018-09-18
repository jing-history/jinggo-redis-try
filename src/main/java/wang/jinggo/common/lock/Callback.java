package wang.jinggo.common.lock;

/**
 * @author wangyj
 * @description
 * @create 2018-09-18 17:24
 **/
public interface Callback {

    /**
     * 成功获取锁后执行方法
     * @return
     * @throws InterruptedException
     */
    public Object onGetLock() throws InterruptedException;

    /**
     * 获取锁超时回调
     * @return
     * @throws InterruptedException
     */
    public Object onTimeout() throws InterruptedException;
}
