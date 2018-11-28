package wang.jinggo.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import wang.jinggo.domain.es.EsLog;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 18:21
 **/
public interface EsLogDao extends ElasticsearchRepository<EsLog, String> {

}
