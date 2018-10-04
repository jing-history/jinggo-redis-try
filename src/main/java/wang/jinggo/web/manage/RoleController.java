package wang.jinggo.web.manage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.Role;
import wang.jinggo.service.RoleService;
import wang.jinggo.util.ResultUtil;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 10:48
 **/
@Slf4j
@RestController
@Api(description = "角色管理接口")
@RequestMapping("/xboot/role")
@Transactional
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/getAllList",method = RequestMethod.GET)
    @ApiOperation(value = "获取全部角色")
    public Result<Object> roleGetAll(){

        List<Role> list = roleService.getAll();
        return new ResultUtil<Object>().setData(list);
    }
}
