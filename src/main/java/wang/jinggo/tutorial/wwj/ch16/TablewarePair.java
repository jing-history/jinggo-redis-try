package wang.jinggo.tutorial.wwj.ch16;

/**
 * 解决死锁的问题
 * @author wangyj
 * @description
 * @create 2018-09-30 11:28
 **/
public class TablewarePair {

    //左手变的餐具
    private final Tableware leftTool;
    //右手变的餐具
    private final Tableware rightTool;

    public TablewarePair(Tableware leftTool,Tableware rightTool){
        this.leftTool = leftTool;
        this.rightTool = rightTool;
    }

    public Tableware getLeftTool() {
        return leftTool;
    }

    public Tableware getRightTool() {
        return rightTool;
    }
}
