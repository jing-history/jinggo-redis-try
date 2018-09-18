package wang.jinggo.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.jinggo.annation.RateLimiter;
import wang.jinggo.common.lock.Callback;
import wang.jinggo.common.lock.RedisDistributedLockTemplate;
import wang.jinggo.common.vo.Result;
import wang.jinggo.util.ResultUtil;

import javax.transaction.Transactional;

/**
 * @author wangyj
 * @description
 * @create 2018-09-18 17:19
 **/
@Slf4j
@Controller
@Api(description = "测试接口")
@Transactional
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisDistributedLockTemplate lockTemplate;

    @RequestMapping(value = "/lockAndLimit",method = RequestMethod.GET)
    @RateLimiter(limit = 1, timeout = 5000)
    @ApiOperation(value = "同步锁限流测试")
    @ResponseBody
    public Result<Object> test(){
        lockTemplate.execute("订单流水号", 5000, new Callback() {
            @Override
            public Object onGetLock() throws InterruptedException {
                //获得锁后要做的事
                log.info("生成订单流水号");
                return null;
            }

            @Override
            public Object onTimeout() throws InterruptedException {
                // 获得锁超时后要做的事
                return null;
            }
        });
        return new ResultUtil<Object>().setData(null);
    }
}
