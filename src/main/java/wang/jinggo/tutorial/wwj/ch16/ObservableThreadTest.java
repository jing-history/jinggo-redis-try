package wang.jinggo.tutorial.wwj.ch16;

import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-29 18:19
 **/
public class ObservableThreadTest {
    public static void main(String[] args) {
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyLifecycle<String>(){
            @Override
            public void onFinish(Thread thread, String result) {
                System.out.println("The result is " + result);
            }
        };

        Observable observable = new ObservableThread(lifecycle, () -> {
            try{
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" finished done.");
            return "Hello Observer";
        });
        observable.start();
    }
}
