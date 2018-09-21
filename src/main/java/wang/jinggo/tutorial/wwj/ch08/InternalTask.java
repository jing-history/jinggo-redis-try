package wang.jinggo.tutorial.wwj.ch08;

/**
 * Runnable 的实现类，主要用于线程池内部，该类会使用RunnableQueue
 * 从queue 中取出某个runnable，运行runnable 的run 方法
 * @author wangyj
 * @description
 * @create 2018-09-21 14:53
 **/
public class InternalTask implements Runnable {

    private final RunnableQueue runnableQueue;

    private volatile boolean running = true;

    public InternalTask(RunnableQueue runnableQueue) {
        this.runnableQueue = runnableQueue;
    }

    @Override
    public void run() {
        //如果当前任务为running 并且没有呗中断，则其将不断从queue 中获取runnable，然后执行run 方法
        while (running && Thread.currentThread().isInterrupted()){
            try{
                Runnable task = runnableQueue.take();
                task.run();
            }catch (Exception e){
                running = false;
                break;
            }
        }
    }
    //停止当前任务，主要在线程池的shutdown 方法中使用
    public void stop(){
        this.running = false;
    }
}
