package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典
 * @author wangyj
 * @description
 * @create 2018-11-19 18:27
 **/
@Data
@Entity
@Table(name = "t_dict")
@TableName("t_dict")
public class Dict extends XbootBaseEntity {

    @ApiModelProperty(value = "字典名称")
    private String title;
    @ApiModelProperty(value = "字典类型")
    private String type;
    @ApiModelProperty(value = "备注")
    private String description;
}
