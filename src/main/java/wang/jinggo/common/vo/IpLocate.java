package wang.jinggo.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyj
 * @description
 * @create 2018-10-19 9:40
 **/
@Data
public class IpLocate implements Serializable {

    private String retCode;

    private City result;
}
