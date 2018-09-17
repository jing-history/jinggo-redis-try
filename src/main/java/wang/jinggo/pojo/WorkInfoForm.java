package wang.jinggo.pojo;

import wang.jinggo.annation.WorkOverTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class WorkInfoForm {

    //定义一个类，更新时候校验
    public interface Update{}

    //定义另外一个类，添加时校验组
    public interface Add{}

    @NotNull(groups = {Update.class})
    @Null(groups = {Add.class})
    Long id;

    @WorkOverTime(max=2)
    int workTime;
}
