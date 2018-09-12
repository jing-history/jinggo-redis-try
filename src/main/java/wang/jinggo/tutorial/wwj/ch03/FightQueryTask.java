package wang.jinggo.tutorial.wwj.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 10:56
 **/
public class FightQueryTask extends Thread implements FightQuery {

    private final String origin;
    private final String destinaction;
    private final List<String> flihtList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destinaction){
        super("["+ airline +"]");
        this.origin = origin;
        this.destinaction = destinaction;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(),origin,destinaction);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.flihtList.add(getName() + "-" + randomVal);
            System.out.printf("The Fight:%s list query successful\n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flihtList;
    }
}
