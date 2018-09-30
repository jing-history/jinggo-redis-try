package wang.jinggo.tutorial.wwj.ch15;

/**
 * @author wangyj
 * @description
 * @create 2018-09-29 10:44
 **/
public class ObservableThread<T> extends Thread implements Observable {

    private final TaskLifecycle<T> lifecycle;
    private final Task<T> task;
    private Cycle cycle;

    //指定Task 的实现，默认情况下使用 EmptyLifecycle
    public ObservableThread(Task<T> task) {
        this(new TaskLifecycle.EmptyLifecycle<>(), task);
    }

    //指定 TaskLifecycle 的同时指定 Task
    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        //Task 不允许为空
        if(task == null)
            throw new IllegalArgumentException("The task is requird.");
        this.lifecycle = lifecycle;
        this.task = task;
    }

    // 重写父类的run 方法，并且修饰为final 类型，不允许子类在次对其进行重写
    @Override
    public final void run() {
        //在执行线程逻辑单元的时候，分别触发相应的事件
        this.update(Cycle.STARTED, null, null);
        try{
            this.update(Cycle.RUNNING, null, null);

            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
        }catch (Exception e){
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if(lifecycle == null)
            return;
        try{
            switch (cycle){
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread(), result);
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread(), e);
                    break;
            }
        }catch (Exception ex){
            if(cycle == Cycle.ERROR) {
                throw ex;
            }
        }
    }

    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
