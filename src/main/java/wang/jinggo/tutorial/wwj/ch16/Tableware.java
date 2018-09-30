package wang.jinggo.tutorial.wwj.ch16;

/**
 * @author wangyj
 * @description
 * @create 2018-09-30 11:28
 **/
public class Tableware {

    //餐具名称
    private final String toolName;

    public Tableware(String toolName) {
        this.toolName = toolName;
    }

    @Override
    public String toString() {
        return "Tableware{" +
                "toolName='" + toolName + '\'' +
                '}';
    }
}
