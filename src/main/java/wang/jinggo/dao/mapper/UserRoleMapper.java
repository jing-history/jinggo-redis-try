package wang.jinggo.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import wang.jinggo.domain.Role;
import wang.jinggo.domain.UserRole;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-09-25 9:11
 **/
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}
