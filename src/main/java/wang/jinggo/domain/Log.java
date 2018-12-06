package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;
import wang.jinggo.util.ObjectUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Map;

@Data
@Entity
@Table(name = "t_log")
@TableName("t_log")
public class Log extends XbootBaseEntity {

    @ApiModelProperty(value = "方法操作名称")
    private String name;

    @ApiModelProperty(value = "请求路径")
    @Column(name = "request_url")
    private String requestUrl;

    @ApiModelProperty(value = "请求类型")
    @Column(name = "request_type")
    private String requestType;

    @ApiModelProperty(value = "请求参数")
    @Column(name = "request_param")
    private String requestParam;

    @ApiModelProperty(value = "请求用户")
    private String username;

    @ApiModelProperty(value = "ip")
    private String ip;

    @ApiModelProperty(value = "ip信息")
    @Column(name = "ip_info")
    private String ipInfo;

    @ApiModelProperty(value = "花费时间")
    @Column(name = "cost_time")
    private Integer costTime;

    @ApiModelProperty(value = "日志类型 0登陆日志 1操作日志")
    private Integer logType;

    /**
     * 转换请求参数为Json
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {

        this.requestParam = ObjectUtil.mapToString(paramMap);
    }
}
