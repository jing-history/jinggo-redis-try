package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import wang.jinggo.base.XbootBaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wangyj
 * @description
 * @create 2018-09-19 14:50
 **/
@Data
@Entity
@Table(name = "t_user")
@TableName("t_user")
public class User extends XbootBaseEntity {
}
