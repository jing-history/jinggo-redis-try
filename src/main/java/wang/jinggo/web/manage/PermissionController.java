package wang.jinggo.web.manage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.Permission;
import wang.jinggo.service.PermissionService;
import wang.jinggo.service.mybatis.IPermissionService;
import wang.jinggo.util.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * xboot/permission/getMenuList
 * @author wangyj
 * @description
 * @create 2018-09-28 9:49
 **/
@Slf4j
@RestController
@Api(description = "菜单/权限管理接口")
@RequestMapping("/xboot/permission")
@CacheConfig(cacheNames = "permission")
@Transactional
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/getAllList",method = RequestMethod.GET)
    @ApiOperation(value = "获取权限菜单树")
    @Cacheable(key = "'allList'")
    public Result<List<Permission>> getAllList(){

        //一级
        List<Permission> list = permissionService.findByLevelOrderBySortOrder(CommonConstant.LEVEL_ONE);
        //二级
        for(Permission p1 : list){
            List<Permission> children1 = permissionService.findByParentIdOrderBySortOrder(p1.getId());
            p1.setChildren(children1);
            //三级
            for(Permission p2 : children1){
                List<Permission> children2 = permissionService.findByParentIdOrderBySortOrder(p2.getId());
                p2.setChildren(children2);
            }
        }
        return new ResultUtil<List<Permission>>().setData(list);
    }

    @RequestMapping(value = "/getMenuList/{userId}",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户页面菜单数据")
    @Cacheable(key = "'userMenuList:'+#userId")
    public Result<List<Permission>> getAllMenuList(@PathVariable String userId){

        //用户所有权限 已排序去重
        List<Permission> list = iPermissionService.findByUserId(userId);
        List<Permission> menuList = new ArrayList<>();

        //筛选一级页面
        for(Permission p : list){
            if(CommonConstant.PERMISSION_PAGE.equals(p.getType())&&CommonConstant.LEVEL_ONE.equals(p.getLevel())){
                menuList.add(p);
            }
        }
        //筛选二级页面
        List<Permission> secondMenuList = new ArrayList<>();
        for(Permission p : list){
            if(CommonConstant.PERMISSION_PAGE.equals(p.getType())&&CommonConstant.LEVEL_TWO.equals(p.getLevel())){
                secondMenuList.add(p);
            }
        }
        //筛选二级页面拥有的按钮权限
        List<Permission> buttonPermissions = new ArrayList<>();
        for(Permission p : list){
            if(CommonConstant.PERMISSION_OPERATION.equals(p.getType())&&CommonConstant.LEVEL_THREE.equals(p.getLevel())){
                buttonPermissions.add(p);
            }
        }

        //匹配二级页面拥有权限
        for(Permission p : secondMenuList){
            List<String> permTypes = new ArrayList<>();
            for(Permission pe : buttonPermissions){
                if(p.getId().equals(pe.getParentId())){
                    permTypes.add(pe.getButtonType());
                }
            }
            p.setPermTypes(permTypes);
        }

        //匹配一级页面拥有二级页面
        for(Permission p : menuList){
            List<Permission> secondMenu = new ArrayList<>();
            for(Permission pe : secondMenuList){
                if(p.getId().equals(pe.getParentId())){
                    secondMenu.add(pe);
                }
            }
            p.setChildren(secondMenu);
        }
        return new ResultUtil<List<Permission>>().setData(menuList);
    }
}
