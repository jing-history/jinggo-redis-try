package wang.jinggo.tutorial.wwj.ch01;

/**
 * @author wangyj
 * @description
 * @create 2018-09-11 9:19
 **/
public class TicketWindow extends Thread {

    public TicketWindow(String name) {
        this.name = name;
    }
    //柜台名称
    private final String name;
    //最多受理50笔业务
    private static final int MAX = 50;
    private static int index;

    @Override
    public void run() {
        while (index <= MAX){
            System.out.println("柜台：" + name +" 当前的号码是：" + (index++));
        }
    }
}

class TicketWindowing{
    public static void main(String[] args) {
        TicketWindow t1 = new TicketWindow("一号机器");
        t1.start();
        TicketWindow t2 = new TicketWindow("二号机器");
        t2.start();
        TicketWindow t3 = new TicketWindow("三号机器");
        t3.start();
        TicketWindow t4 = new TicketWindow("四号机器");
        t4.start();

    }
}
