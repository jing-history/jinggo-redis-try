package wang.jinggo.tutorial.wwj.ch16;

/**
 * 下面线程解决刀叉死锁的问题，因为在同一个时间只能有一个线程获得刀和叉
 * @author wangyj
 * @description
 * @create 2018-09-30 11:29
 **/
public class EatNoodleThread2 extends Thread {

    private final String name;

    private final TablewarePair tablewarePair;

    public EatNoodleThread2(String name, TablewarePair tablewarePair){
        this.name = name;
        this.tablewarePair = tablewarePair;
    }

    @Override
    public void run() {
        while (true){
            this.eat();
        }
    }

    //吃面条的过程
    private void eat() {
        synchronized (tablewarePair) {
            System.out.println(name + "take up " + tablewarePair.getLeftTool() + "(left)");
            System.out.println(name + "take up " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + "is eating now.");
            System.out.println(name + " put down " + tablewarePair.getRightTool() + "(right)");
            System.out.println(name + " put down " + tablewarePair.getLeftTool() + "(left)");
        }
    }

    public static void main(String[] args) {

    }
}
