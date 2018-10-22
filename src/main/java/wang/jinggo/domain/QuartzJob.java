package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;
import wang.jinggo.common.constant.CommonConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangyj
 * @description
 * @create 2018-10-22 15:00
 **/
@Data
@Entity
@Table(name = "t_quartz_job")
@TableName("t_quartz_job")
public class QuartzJob extends XbootBaseEntity {

    @ApiModelProperty(value = "任务类名")
    @Column(name = "job_class_name")
    private String jobClassName;

    @ApiModelProperty(value = "cron表达式")
    @Column(name = "cron_expression")
    private String cronExpression;

    @ApiModelProperty(value = "参数")
    private String parameter;

    @ApiModelProperty(value = "备注")
    private String description;

    @ApiModelProperty(value = "状态 0正常 -1停止")
    private Integer status = CommonConstant.STATUS_NORMAL;
}
