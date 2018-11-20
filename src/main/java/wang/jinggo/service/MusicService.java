package wang.jinggo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import wang.jinggo.base.XbootBaseService;
import wang.jinggo.domain.MusicLrc;
import wang.jinggo.pojo.SearchVo;

/**
 * Created by gz12 on 2018-11-20.
 */
public interface MusicService extends XbootBaseService<MusicLrc,String> {

    /**
     *  条件查询
     * title
     * createDate
     * @param pageable
     * @return
     */
    Page<MusicLrc> findByCondition(SearchVo searchVo, Pageable pageable);
}
