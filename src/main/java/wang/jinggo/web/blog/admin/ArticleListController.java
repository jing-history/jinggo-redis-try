package wang.jinggo.web.blog.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wang.jinggo.common.vo.PageVo;
import wang.jinggo.common.vo.Result;
import wang.jinggo.domain.blog.BlogArticle;
import wang.jinggo.pojo.SearchVo;
import wang.jinggo.service.blog.ArticleListService;
import wang.jinggo.util.PageUtil;
import wang.jinggo.util.ResultUtil;

import java.util.List;

/**
 * 博客内容列表
 * @author wangyj
 * @description
 * @create 2018-12-06 10:25
 **/

@Slf4j
@RestController
@Api(description = "博客内容管理接口")
@RequestMapping("/xboot/blog")
@Transactional
public class ArticleListController {

    private final static Logger LOG =  LoggerFactory.getLogger(ArticleListController.class);

    @Autowired
    private ArticleListService articleListService;

    @RequestMapping(value = "/getByCondition",method = RequestMethod.GET)
    @ApiOperation(value = "多条件分页获取内容列表")
    public Result<Page<BlogArticle>> getByCondition(SearchVo searchVo, @ModelAttribute PageVo pageVo){

        LOG.info("====>>> " + searchVo.toString());
        Page<BlogArticle> page = articleListService.findByCondition(searchVo, PageUtil.initPage(pageVo));
        List<BlogArticle> list = articleListService.getAll();
        return new ResultUtil<Page<BlogArticle>>().setData(page);
    }
}
