package wang.jinggo.web.manage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.Department;
import wang.jinggo.service.DepartmentService;
import wang.jinggo.util.ResultUtil;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-30 15:36
 **/
@Slf4j
@RestController
@Api(description = "部门管理接口")
@RequestMapping("/xboot/department")
@CacheConfig(cacheNames = "department")
@Transactional
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/getByParentId/{parentId}",method = RequestMethod.GET)
    @ApiOperation(value = "通过id获取")
    @Cacheable(key = "#parentId")
    public Result<List<Department>> getByParentId(@PathVariable String parentId){

        List<Department> list = departmentService.findByParentIdOrderBySortOrder(parentId);
        // lambda表达式
        list.forEach(item -> {
            if(!CommonConstant.PARENT_ID.equals(item.getParentId())){
                Department parent = departmentService.get(item.getParentId());
                item.setParentTitle(parent.getTitle());
            }else{
                item.setParentTitle("一级部门");
            }
        });
        return new ResultUtil<List<Department>>().setData(list);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ApiOperation(value = "添加")
    @CacheEvict(key = "#department.parentId")
    public Result<Department> add(@ModelAttribute Department department){

        Department d = departmentService.save(department);
        // 如果不是添加的一级 判断设置上级为父节点标识
        if(!CommonConstant.PARENT_ID.equals(department.getParentId())){
            Department parent = departmentService.get(department.getParentId());
            if(parent.getIsParent()==null||!parent.getIsParent()){
                parent.setIsParent(true);
                departmentService.update(parent);
                // 更新上级节点的缓存
                redisTemplate.delete("department::" + parent.getParentId());
            }
        }
        return new ResultUtil<Department>().setData(d);
    }
}