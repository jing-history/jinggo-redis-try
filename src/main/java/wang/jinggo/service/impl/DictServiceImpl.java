package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.DictDao;
import wang.jinggo.domain.Dict;
import wang.jinggo.service.DictService;

import java.util.List;

/**
 * 字典接口实现
 * @author wangyj
 * @description
 * @create 2018-11-21 14:03
 **/
@Slf4j
@Service
@Transactional
public class DictServiceImpl implements DictService {

    @Autowired
    private DictDao dictDao;

    @Override
    public DictDao getRepository() {
        return dictDao;
    }

    @Override
    public Dict findByType(String type) {
        List<Dict> list = dictDao.findByType(type);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Dict> findByTitleOrTypeLike(String key) {
        return dictDao.findByTitleOrTypeLike(key);
    }
}
