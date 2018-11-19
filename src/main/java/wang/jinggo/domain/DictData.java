package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;
import wang.jinggo.common.constant.CommonConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author wangyj
 * @description
 * @create 2018-11-19 18:29
 **/
@Data
@Entity
@Table(name = "t_dict_data")
@TableName("t_dict_data")
public class DictData extends XbootBaseEntity {

    @ApiModelProperty(value = "数据名称")
    private String title;
    @ApiModelProperty(value = "数据值")
    private String value;
    @ApiModelProperty(value = "排序值")
    @Column(name = "sort_order", precision = 10, scale = 2)
    private BigDecimal sortOrder;
    @ApiModelProperty(value = "是否启用 0启用 -1禁用")
    private Integer status = CommonConstant.STATUS_NORMAL;
    @ApiModelProperty(value = "备注")
    private String description;
    @ApiModelProperty(value = "所属字典")
    @Column(name = "dict_id")
    private String dictId;
}
