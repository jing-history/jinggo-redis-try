package wang.jinggo.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 前后端交互数据标准
 * @author wangyj
 * @description
 * @create 2018-09-18 18:33
 **/
@Data
public class Result<T> implements Serializable {

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 失败消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 结果对象
     */
    private T result;
}
