package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 角色
 * @author wangyj
 * @description
 * @create 2018-09-22 17:42
 **/
@Data
@Entity
@Table(name = "t_role")
@TableName("t_role")
public class Role extends XbootBaseEntity {

    @ApiModelProperty(value = "角色名 以ROLE_开头")
    private String name;

    @ApiModelProperty(value = "是否为注册默认角色")
    @Column(name = "default_role")
    private Boolean defaultRole;

    @ApiModelProperty(value = "备注")
    private String description;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "拥有权限")
    private List<Permission> permissions;
}
