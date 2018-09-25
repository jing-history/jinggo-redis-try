package wang.jinggo.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import wang.jinggo.domain.Permission;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 11:40
 **/
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Permission> findByUserId(@Param("userId") String userId);
}
