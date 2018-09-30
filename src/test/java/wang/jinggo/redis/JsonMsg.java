package wang.jinggo.redis;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-30 9:08
 **/
public class JsonMsg {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Proudct p1 = new Proudct("ASD11101","2","1500","3000");
        Proudct p2 = new Proudct("ASD11102","3","1500","4500");
        List<Proudct> proudcts = new ArrayList<>();
        proudcts.add(p1);
        proudcts.add(p2);

        Msg msg = new Msg("CRM系统","AS00000000001","北京成龙","零售","2018-09-30","物料拆单完成");
        msg.setProudcts(proudcts);
        System.out.println(gson.toJson(msg));

    }
}

class Msg{
    private String fromSys;
    private String order;
    private String customer;
    private String sourceSys;
    private String orderDate;
    private String orderStatus;

    private List<Proudct> proudcts = null;

    public Msg(String fromSys, String order, String customer, String sourceSys, String orderDate, String orderStatus) {
        this.fromSys = fromSys;
        this.order = order;
        this.customer = customer;
        this.sourceSys = sourceSys;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public void setProudcts(List<Proudct> proudcts) {
        this.proudcts = proudcts;
    }

    public List<Proudct> getProudcts() {
        return proudcts;
    }
}

class Proudct{
    private String id;
    private String number;
    private String price;
    private String amount;

    public Proudct(String id, String number, String price, String amount) {
        this.id = id;
        this.number = number;
        this.price = price;
        this.amount = amount;
    }
}
