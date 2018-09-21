package wang.jinggo.tutorial.wwj.ch08;

/**
 * 任务队列，主要用于缓存提交到线程池中的任务
 * @author wangyj
 * @description
 * @create 2018-09-21 14:32
 **/
public interface RunnableQueue {

    //当有新的任务进来时首先会offer 到队列中
    void offer(Runnable runnable);

    //工作线程通过take 方法获取Runnable
    Runnable take() throws InterruptedException;

    //获取任务队列中任务的数量
    int size();
}
