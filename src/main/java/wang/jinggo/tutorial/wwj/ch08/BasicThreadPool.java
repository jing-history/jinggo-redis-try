package wang.jinggo.tutorial.wwj.ch08;

import com.zaxxer.hikari.util.UtilityElf;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangyj
 * @description
 * @create 2018-09-21 20:06
 *
public class BasicThreadPool extends Thread implements ThreadPool {

    //初始化线程数量
    private final int initSize;

    //线程池最大线程数量
    private final int maxSize;

    //线程池核心线程数量
    private final int coreSize;

    //当前活跃的线程数量
    private int acticeCount;

    //创建线程所需的工厂
    //private final ThreadFactory threadFactory;

    //任务队列
    //private final RunnableQueue runnableQueue;

    //线程池是否已经被shutdown
    private volatile boolean isShutdown = false;

    //工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keeepAliveTime;

    private final TimeUnit timeUnit;

    //构造时需要传递的参数：初始化线程数量，最大线程数量，核心线程数量，任务队列的最大数量
    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize){
        this(initSize, maxSize,coreSize,DEFAULT_THREAD_FACTORY, queueSize,DEFAULT_DENY_POLICY, 10 ,TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, int i, TimeUnit seconds,
                           long keepAliveTime, TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keeepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    //初始化时，先创建initSize 个线程
    private void init() {
        start();

        for (int i = 0; i < initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        //创建任务线程，并且启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.acticeCount++;
        thread.start();
    }

    private void removeThread(){
        //从线程池中移除某个线程
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.acticeCount--;
    }

    @Override
    public void execute(Runnable runnable) {
        if(this.isShutdown)
            throw new IllegalStateException("The thread pool is destroy");
        //提交任务
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {

    }

    @Override
    public int getInitSize() {
        return 0;
    }

    @Override
    public int getMaxSize() {
        return 0;
    }

    @Override
    public int getCoreSize() {
        return 0;
    }

    @Override
    public int getQueueSize() {
        return 0;
    }

    @Override
    public int getActiveCount() {
        return 0;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    //ThreadTask 只是InternalTask 和Thread 的一个组合
    private static class ThreadTask{
        Thread thread;
        InternalTask internalTask;
        public ThreadTask(Thread thread, InternalTask internalTask){
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory{
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);
        private static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUP_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group,runnable, "thread-pool-" + COUNTER.getAndDecrement());
        }
    }
}*/

