package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import wang.jinggo.base.XbootBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangyj
 * @description
 * @create 2018-10-23 17:50
 **/
@Data
@ToString
@Entity
@Table(name = "loves")
@TableName("loves")
public class TimeAxis extends XbootBaseEntity {

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "图片")
    private String figureImg;

    @ApiModelProperty(value = "说明")
    private String figureMsg;

    @ApiModelProperty(value = "图文说明")
    private String figcaption;

}
