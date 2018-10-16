package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_role_permission")
@TableName("t_role_permission")
public class RolePermission extends XbootBaseEntity {

    @ApiModelProperty(value = "角色id")
    @Column(name = "role_id")
    private String roleId;

    @ApiModelProperty(value = "权限id")
    @Column(name = "permission_id")
    private String permissionId;
}
