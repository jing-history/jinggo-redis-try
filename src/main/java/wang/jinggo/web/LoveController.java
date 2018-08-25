package wang.jinggo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wang.jinggo.dao.LoveRepository;
import wang.jinggo.domain.Love;
import wang.jinggo.domain.vo.LoveForm;
import wang.jinggo.util.DTOUtil;

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
public class LoveController {

    @Autowired
    private LoveRepository loveRepository;

    private static final int PAGE_SIZE = 10000;
    private static SimpleDateFormat FORMATDATE = new SimpleDateFormat("yyyy,MM,dd");

    @RequestMapping(value = "")
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
    }
}
