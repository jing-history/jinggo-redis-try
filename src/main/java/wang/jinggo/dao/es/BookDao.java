package wang.jinggo.dao.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import wang.jinggo.domain.es.Book;

import java.util.List;

/**
 * Created by gz12 on 2018-10-05.
 */
public interface BookDao extends CrudRepository<Book, String> {

    public List<Book> getByMessage(String key);

 //   Page<Book> getByMessage(String key, PageRequest request);
}
