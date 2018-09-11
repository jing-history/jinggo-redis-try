package wang.jinggo.tutorial.wwj.ch01;

import java.sql.ResultSet;

/**
 * @author wangyj
 * @description
 * @create 2018-09-11 9:32
 **/
public interface RowHandler<T> {
    T handle(ResultSet rs);
}
