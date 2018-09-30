package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.Department;
import wang.jinggo.service.DepartmentService;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-30 18:20
 **/
@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Override
    public XbootBaseDao<Department, String> getRepository() {
        return null;
    }

    @Override
    public List<Department> findByParentIdOrderBySortOrder(String parentId) {
        return null;
    }


}
