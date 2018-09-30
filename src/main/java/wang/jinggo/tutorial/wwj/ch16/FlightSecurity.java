package wang.jinggo.tutorial.wwj.ch16;

/**
 * Single Thread Execution 模式
 * 是指同一时刻只能有一个线程去访问共享资源，
 * 就像独木桥一样每次只允许一人通过
 * @author wangyj
 * @description
 * @create 2018-09-30 11:05
 **/
public class FlightSecurity {

    private int count = 0;

    //登机牌
    private String boardingPass = "null";
    //身份证
    private String idCard = "null";

    //使用 synchronized 排他性
    public synchronized void pass(String boardingPass, String idCard){
        this.boardingPass = boardingPass;
        this.idCard = idCard;
        this.count++;
        check();
    }

    private void check() {
        if(boardingPass.charAt(0) != idCard.charAt(0))
            throw new RuntimeException("========Exception=======" + toString());
    }

    @Override
    public String toString() {
        return "FlightSecurity{" +
                "count=" + count +
                ", boardingPass='" + boardingPass + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
