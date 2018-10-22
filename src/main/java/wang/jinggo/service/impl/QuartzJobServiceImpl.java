package wang.jinggo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.dao.QuartzJobDao;
import wang.jinggo.domain.QuartzJob;
import wang.jinggo.service.QuartzJobService;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-10-22 15:15
 **/

@Slf4j
@Service
@Transactional
public class QuartzJobServiceImpl implements QuartzJobService {

    @Autowired
    private QuartzJobDao quartzJobDao;

    @Override
    public List<QuartzJob> findByJobClassName(String jobClassName) {
        return null;
    }

    @Override
    public QuartzJobDao getRepository() {
        return quartzJobDao;
    }
}
