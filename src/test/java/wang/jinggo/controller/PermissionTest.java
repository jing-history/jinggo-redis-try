package wang.jinggo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.domain.Permission;
import wang.jinggo.service.mybatis.IPermissionService;
import wang.jinggo.util.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-28 10:14
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionTest {

    @Autowired
    private IPermissionService iPermissionService;

    @Test
    public void getAllMenuList(){

        String userId = "4363087427670016";
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
        log.info(menuList.toString());
    }
}
