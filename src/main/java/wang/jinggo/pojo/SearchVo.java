package wang.jinggo.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 我的空间查询条件
 */
@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;

    // 这个是时间轴里面的title
    private String title;

    //================2. 播放器条件

    private String name;

}
