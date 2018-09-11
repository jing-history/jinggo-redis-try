package wang.jinggo.tutorial.wwj.ch01;

/**
 * 模板模式
 * @author wangyj
 * @description
 * @create 2018-09-11 9:12
 **/
public class TemplateMethod {

    public final void print(String message){
        System.out.println("####################");
        wrapPrint(message);
        System.out.println("####################");
    }

    protected void wrapPrint(String message){}

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod(){
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };
        t1.print("Hello Thread");
    }
}
