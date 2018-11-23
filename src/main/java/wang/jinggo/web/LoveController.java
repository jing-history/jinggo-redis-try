package wang.jinggo.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wang.jinggo.common.constant.CommonConstant;
import wang.jinggo.common.vo.Result;
import wang.jinggo.dao.LoveRepository;
import wang.jinggo.dao.MusicLrcDao;
import wang.jinggo.dao.TimeAxisDao;
import wang.jinggo.domain.Love;
import wang.jinggo.domain.MusicLrc;
import wang.jinggo.domain.TimeAxis;
import wang.jinggo.domain.vo.LoveForm;
import wang.jinggo.util.DTOUtil;
import wang.jinggo.util.ResultUtil;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wangyj
 * @description
 * @create 2018-08-25 14:25
 **/
@Controller
@Api(description = "静态页面时间轴内容接口")
@RequestMapping("/love")
public class LoveController {

    @Autowired
    private LoveRepository loveRepository;

    @Autowired
    private TimeAxisDao timeAxisDao;
    @Autowired
    private MusicLrcDao musicLrcDao;


    private static final int PAGE_SIZE = 10000;
    private static SimpleDateFormat FORMATDATE = new SimpleDateFormat("yyyy,MM,dd");

    /*@RequestMapping(value = "")
    public String love(Model model) throws IOException {

        List<LoveForm> loveForms = new ArrayList<>();
        List<Love> loves = loveRepository.findAll();
        for (Love love : loves) {
            LoveForm loveForm = DTOUtil.map(love, LoveForm.class);
            Date nowDate = love.getCreatedAt();
            String dateStr = FORMATDATE.format(nowDate);
            loveForm.setFormatDate(dateStr);

            loveForms.add(loveForm);
        }

        model.addAttribute("loves", loveForms);
        return "love";
    }*/

    /*@RequestMapping(value = "/data")
    public String timelist(Model model) throws IOException {

        List<LoveForm> loveForms = new ArrayList<>();
        List<TimeAxis> loves = timeAxisDao.findAll();
        for (TimeAxis love : loves) {
            LoveForm loveForm = DTOUtil.map(love, LoveForm.class);
            Date nowDate = love.getCreateTime();
            String dateStr = FORMATDATE.format(nowDate);
            loveForm.setFormatDate(dateStr);

            loveForms.add(loveForm);
        }

        model.addAttribute("loves", loveForms);
        return "love";
    }*/

    /**
     * 动静分离 前端Aajax 请求
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<LoveForm>> timeData(Model model) throws IOException {
        List<LoveForm> loveForms = new ArrayList<>();
        List<TimeAxis> loves = timeAxisDao.findAll();
        for (TimeAxis love : loves) {
            LoveForm loveForm = DTOUtil.map(love, LoveForm.class);
            Date nowDate = love.getCreateTime();
            String dateStr = FORMATDATE.format(nowDate);
            loveForm.setFormatDate(dateStr);

            loveForms.add(loveForm);
        }

        return new ResultUtil<List<LoveForm>>().setData(loveForms);
    }

    @RequestMapping(value = "music", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<MusicLrc>> getMusic(@RequestParam String status) throws IOException {
        //查询状态为0 的歌曲
        List<MusicLrc> musicLrcs = musicLrcDao.findByStatus(CommonConstant.STATUS_NORMAL);
        return new ResultUtil<List<MusicLrc>>().setData(musicLrcs);
    }

    /**
     * 动静分离 前端Aajax 请求 获取歌词
     * @param name
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "musiclrc", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getMusicLrc(@RequestParam String name) throws IOException {
     //   MusicLrc musicLrc = musicLrcDao.findByName(name);
        MusicLrc musicLrc = musicLrcDao.findByCode(name);
        if(musicLrc != null){
            String lrc = musicLrc.getContent();
            return new ResultUtil<String>().setData(lrc);
        }
        return new ResultUtil<String>().setData("No Data");
    }
}
