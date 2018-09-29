package wang.jinggo.tutorial.wwj.ch16;

/**
 * @author wangyj
 * @description
 * @create 2018-09-29 10:31
 **/
public interface TaskLifecycle<T> {

    //任务启动时会触发
    void onStart(Thread thread);

    //任务正在运行是会触发
    void onRunning(Thread thread);

    //任务运行结束时候会触发,其中result 是任务执行结束后的结果
    void onFinish(Thread thread, T result);

    //任务执行报错时触发
    void onError(Thread thread, Exception e);

    //生命周期接口的空实现(Adapter)
    class EmptyLifecycle<T> implements TaskLifecycle<T>{

        @Override
        public void onStart(Thread thread) {

        }

        @Override
        public void onRunning(Thread thread) {

        }

        @Override
        public void onFinish(Thread thread, T result) {

        }

        @Override
        public void onError(Thread thread, Exception e) {

        }
    }
}
