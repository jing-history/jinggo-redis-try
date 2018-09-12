package wang.jinggo.tutorial.wwj.ch03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author wangyj
 * @description
 * @create 2018-09-12 11:03
 **/
public class FightQueryExample {

    //1. 航空公司
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        List<String> results = serach("SH", "BJ");
        System.out.println("=====================result===================");
        results.forEach(System.out::println);
    }

    private static List<String> serach(String original, String dest) {

        final List<String> result = new ArrayList<>();

        //2. 创建查询航班信息的线程列表
        List<FightQueryTask> tasks = fightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(toList());

        // 3.分别启动这几个线程
        tasks.forEach(Thread::start);

        // 4. 分别调用每个线程的join 方法，阻塞当前线程
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 5.在此之前，当前线程会阻塞住，获取每个查询线程的结果，并且接入到result中
        tasks.stream().map(FightQuery::get).forEach(result::addAll);

        return result;
    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest) {
        return new FightQueryTask(fight, original, dest);
    }
}
