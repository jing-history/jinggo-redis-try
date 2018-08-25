package wang.jinggo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.jinggo.domain.Love;

import javax.transaction.Transactional;

/**
 * @author wangyj
 * @description
 * @create 2018-08-25 14:26
 **/
@Repository
@Transactional
public interface LoveRepository extends JpaRepository<Love, Long> {
}
