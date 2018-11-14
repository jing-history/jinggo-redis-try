package wang.jinggo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.dao.EsLogDao;
import wang.jinggo.domain.EsLog;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.service.EsLogService;

/**
 * @author wangyj
 * @description
 * @create 2018-10-04 18:19
 **/
@Service
@Transactional
@Slf4j
public class EsLogServiceImpl implements EsLogService {

    @Autowired
    private EsLogDao logDao;

    @Override
    public EsLog saveLog(EsLog esLog) {

        return logDao.save(esLog);
    }

    @Override
    public void deleteLog(String id) {

        logDao.deleteById(id);
    }

    @Override
    public void deleteAll() {

        logDao.deleteAll();
    }

    @Override
    public Page<EsLog> getLogList(Pageable pageable) {

        return logDao.findAll(pageable);
    }

    @Override
    public Page<EsLog> searchLog(String key, SearchVo searchVo, Pageable pageable) {

        if(StrUtil.isBlank(key)&&StrUtil.isBlank(searchVo.getStartDate())){
            return null;
        }

        QueryBuilder qb;


        QueryBuilder qb1 = QueryBuilders.multiMatchQuery(key, "requestUrl", "requestType","requestParam","username","ip","ipInfo");

        //仅有key
        if(StrUtil.isNotBlank(key)&&StrUtil.isBlank(searchVo.getStartDate())&&StrUtil.isBlank(searchVo.getEndDate())){
            qb = qb1;
        }else if(StrUtil.isBlank(key)&&StrUtil.isNotBlank(searchVo.getStartDate())&&StrUtil.isNotBlank(searchVo.getEndDate())){
            //仅有时间范围
            Long start = DateUtil.parse(searchVo.getStartDate()).getTime();
            Long end = DateUtil.endOfDay(DateUtil.parse(searchVo.getEndDate())).getTime();
            QueryBuilder qb2 = QueryBuilders.rangeQuery("timeMillis").gte(start).lte(end);
            qb = qb2;
        }else{
            //两者都有
            Long start = DateUtil.parse(searchVo.getStartDate()).getTime();
            Long end = DateUtil.endOfDay(DateUtil.parse(searchVo.getEndDate())).getTime();
            QueryBuilder qb2 = QueryBuilders.rangeQuery("timeMillis").gte(start).lte(end);
            qb = QueryBuilders.boolQuery().must(qb1).must(qb2);
        }

        //不使用的，举例放在这里
        QueryBuilder qb3 = QueryBuilders.boolQuery()//
                .must(QueryBuilders.termQuery("schoolId", "0"))// 单个
                .must(QueryBuilders.termQuery("sex", "0"))//
                .must(QueryBuilders.termsQuery("specialtyId", "71", "72"))// 多选
                .must(QueryBuilders.termsQuery("educationId", "2", "3", "4"))//
                .must(QueryBuilders.matchQuery("addr", "海"));

        //多字段搜索
        return logDao.search(qb, pageable);
    }
}
