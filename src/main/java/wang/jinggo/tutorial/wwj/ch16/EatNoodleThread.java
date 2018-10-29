package wang.jinggo.tutorial.wwj.ch16;

import org.springframework.security.core.parameters.P;

/**
 * @author wangyj
 * @description
 * @create 2018-09-30 11:29
 **/
public class EatNoodleThread extends Thread {

    private final String name;

    //左手变的餐具
    private final Tableware leftTool;
    //右手变的餐具
    private final Tableware rightTool;

    public EatNoodleThread(String name,Tableware leftTool,Tableware rightTool){
        this.name = name;
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    @Override
    public void run() {
        while (true){
            this.eat();
        }
    }

    //吃面条的过程
    private void eat() {
        synchronized (leftTool) {
            System.out.println(name + "take up " + leftTool + "(left)");
            synchronized (rightTool){
                System.out.println(name + "take up " + rightTool + "(right)");
                System.out.println(name + "is eating now.");
                System.out.println(name + " put down " + rightTool + "(right)");
            }
            System.out.println(name + " put down " + leftTool + "(left)");
        }
    }

    public static void main(String[] args) {
        Tableware fork = new Tableware("fork");
        Tableware knife = new Tableware("knife");
        new EatNoodleThread("A",fork,knife).start();
        new EatNoodleThread("B",knife,fork).start();
    }
}
