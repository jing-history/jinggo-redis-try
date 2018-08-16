package wang.jinggo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.jinggo.domain.Note;

import javax.transaction.Transactional;

/**
 * @author wangyj
 * @description
 * @create 2018-08-16 10:47
 **/
@Repository
@Transactional
public interface NoteRepository extends JpaRepository<Note, Long> {
}
