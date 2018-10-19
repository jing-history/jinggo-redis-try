package wang.jinggo.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyj
 * @description
 * @create 2018-10-19 9:41
 **/
@Data
public class City implements Serializable {

    String country;

    String province;

    String city;
}
