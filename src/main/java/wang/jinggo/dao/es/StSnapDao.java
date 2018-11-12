package wang.jinggo.dao.es;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.repository.CrudRepository;
import wang.jinggo.domain.es.Book;
import wang.jinggo.domain.es.ElaStSnap;

/**
 * @author wangyj
 * @description
 * @create 2018-11-12 15:10
 **/
public interface StSnapDao extends CrudRepository<ElaStSnap, String>  {

    Page<ElaStSnap> search(SearchQuery searchQuery);
}
