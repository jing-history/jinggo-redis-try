package wang.jinggo.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import wang.jinggo.base.XbootBaseEntity;
import wang.jinggo.common.constant.CommonConstant;

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
@Table(name = "t_music_lrc")
@TableName("t_music_lrc")
public class MusicLrc extends XbootBaseEntity {

    @ApiModelProperty(value = "歌名")
    private String name;

    @ApiModelProperty(value = "歌名代码")
    private String code;

    @ApiModelProperty(value = "歌词")
    private String content;

    @ApiModelProperty(value = "状态 默认0正常 -1禁用")
    private Integer status = CommonConstant.USER_STATUS_NORMAL;
}
