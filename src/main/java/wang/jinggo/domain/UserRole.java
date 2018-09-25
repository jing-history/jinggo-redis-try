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

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 9:18
 **/
@Data
@Entity
@Table(name = "t_user_role")
@TableName("t_user_role")
public class UserRole extends XbootBaseEntity {

    @ApiModelProperty(value = "用户唯一id")
    @Column(name = "user_id")
    private String userId;

    @ApiModelProperty(value = "角色唯一id")
    @Column(name = "role_id")
    private String roleId;

    @Transient
    @TableField(exist=false)
    @ApiModelProperty(value = "角色名")
    @Column(name = "role_name")
    private String roleName;
}
