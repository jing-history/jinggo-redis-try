package wang.jinggo.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 自定义接口 不会创建接口的实例 必须加此注解
 * @author wangyj
 * @description
 * @create 2018-09-19 15:28
 **/
@NoRepositoryBean
public interface XbootBaseDao<E, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}
