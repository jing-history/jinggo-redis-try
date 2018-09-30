package wang.jinggo.service;

import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.Department;

import java.util.List;

/**
 * 部门接口
 * @author wangyj
 * @description
 * @create 2018-09-30 18:17
 **/
public interface DepartmentService extends XbootBaseService<Department,String> {

    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);
}
