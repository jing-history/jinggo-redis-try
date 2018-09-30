package wang.jinggo.tutorial.wwj.ch16;

/**
 * @author wangyj
 * @description
 * @create 2018-09-30 11:10
 **/
public class FlightSecurityTest {

    static class Passengers extends Thread{

        //机场安检类
        private final FlightSecurity flightSecurity;
        //旅客的身份证
        private final String idCard;
        //旅客的登机牌
        private final String boardingPass;

        public Passengers(FlightSecurity flightSecurity, String idCard, String boardingPass){
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true) {
                //旅客不断过安检
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }

    public static void main(String[] args) {
        //定义三个旅客，身份证和登机牌首字母相同
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passengers(flightSecurity, "A123456","AF123456").start();
        new Passengers(flightSecurity, "B123456","BF123456").start();
        new Passengers(flightSecurity, "C123456","CF123456").start();
    }
}
