package wang.jinggo.web.mine;

import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.common.vo.PageVo;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.QuartzJob;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.domain.User;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.service.TimeAxisService;
import wang.jinggo.util.PageUtil;
import wang.jinggo.util.ResultUtil;
import wang.jinggo.web.WorkInfoController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 时间轴管理
 * @author wangyj
 * @description
 * @create 2018-10-23 17:39
 **/

@Slf4j
@RestController
@Api(description = "时间轴管理接口")
@RequestMapping("/xboot/mine")
@Transactional
public class TimeAxisController {

    private final static Logger LOG =  LoggerFactory.getLogger(TimeAxisController.class);

    @Autowired
    TimeAxisService timeAxisService;

    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取内容列表")
    public Result<Page<TimeAxis>> getByCondition(SearchVo searchVo, @ModelAttribute PageVo pageVo){

        LOG.info("====>>> " + searchVo.toString());
        Page<TimeAxis> page = timeAxisService.findByCondition(searchVo, PageUtil.initPage(pageVo));
        return new ResultUtil<Page<TimeAxis>>().setData(page);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加时间轴事件")
    public Result<Object> addTime(@ModelAttribute TimeAxis timeAxis){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        timeAxis.setCreateBy(user.getUsername());
        timeAxis.setCreateTime(new Date());
        timeAxisService.save(timeAxis);
        return new ResultUtil<Object>().setSuccessMsg("添加时间轴事件成功");
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ApiOperation(value = "修改时间轴事件")
    public Result<Object> editTime(@ModelAttribute TimeAxis timeAxis){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        timeAxis.setUpdateBy(user.getUsername());
        timeAxis.setUpdateTime(new Date());
        timeAxisService.update(timeAxis);
        return new ResultUtil<Object>().setSuccessMsg("修改时间轴事件成功");
    }
}
