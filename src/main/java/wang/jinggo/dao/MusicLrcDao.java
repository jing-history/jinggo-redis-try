package wang.jinggo.dao;

import wang.jinggo.base.XbootBaseDao;
import wang.jinggo.domain.MusicLrc;

import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-10-23 18:10
 **/
public interface MusicLrcDao extends XbootBaseDao<MusicLrc,String>  {

    MusicLrc findByName(String name);
}
