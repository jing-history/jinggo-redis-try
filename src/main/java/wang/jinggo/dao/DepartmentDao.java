package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.Department;

import java.util.List;

/**
 * 部门数据处理层
 * @author wangyj
 * @description
 * @create 2018-09-25 9:08
 **/
public interface DepartmentDao extends XbootBaseDao<Department,String> {

    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(String parentId);

}
