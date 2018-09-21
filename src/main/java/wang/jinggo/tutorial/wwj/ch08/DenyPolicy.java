package wang.jinggo.tutorial.wwj.ch08;

/**
 * 主要用于当Queue 中的runnable 达到了limit 上限时候，
 * 决定采用何种策略通知提交者。
 * @author wangyj
 * @description
 * @create 2018-09-21 14:40
 **/
@FunctionalInterface
public interface DenyPolicy {

    void reject(Runnable runnable, ThreadPool threadPool);

    //该拒绝策略会直接将任务丢弃
    class DiscardDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            // do nothing
        }
    }

    //该拒绝策略会向任务提交者抛出异常
    class AbortDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
           throw new RunnableDenyException("The runable " + runnable + " will be abort.");
        }
    }

    //该拒绝策略会使任务在提交者所在的线程中执行任务
    class RunnerDenyPolicy implements DenyPolicy{

        @Override
        public void reject(Runnable runnable, ThreadPool threadPool) {
            if(!threadPool.isShutdown()){
                runnable.run();
            }
        }
    }
}
