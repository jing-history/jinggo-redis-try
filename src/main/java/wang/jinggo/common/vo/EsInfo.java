package wang.jinggo.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 18:11
 **/
@Data
public class EsInfo implements Serializable {

    private String cluster_name;

    private String status;

    private String number_of_nodes;

    private Integer count;
}

