package wang.jinggo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchVo implements Serializable {

    private String startDate;

    private String endDate;

    // 这个是时间轴里面的title
    private String title;
}
