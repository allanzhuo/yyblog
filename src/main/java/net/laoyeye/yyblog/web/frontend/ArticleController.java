package net.laoyeye.yyblog.web.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.WebUtils;
import net.laoyeye.yyblog.mapper.ArticleMapper;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.SettingDO;
import net.laoyeye.yyblog.model.query.CommentQuery;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.CateService;
import net.laoyeye.yyblog.service.CommentService;
import net.laoyeye.yyblog.service.SettingService;
import net.laoyeye.yyblog.service.TagReferService;
import net.laoyeye.yyblog.service.UserService;
import net.laoyeye.yyblog.web.BaseController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台文章Controller
 * @author 小卖铺的老爷爷
 * @date 2018年5月5日
 * @website www.laoyeye.net
 */
@Api(description="文章查询")
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController{
    @Autowired
    private ArticleService articleService;
    @Autowired
    private SettingService settingService;
    @Autowired
    private CateService cateService;
    @Autowired
    private TagReferService tagReferService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentService commentService;
    
    @ApiOperation(value="文章查询接口")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Long")
    @GetMapping("/{id}")
    public String index(Model model, @PathVariable("id") Long id) {
        try {
            articleService.updateViewsById(id);
        } catch (Exception ignore) {
        }
        List<SettingDO> settings = settingService.listAll();
        Map<String,Object> map = new HashMap<String,Object>();
        for (SettingDO setting : settings) {
            map.put(setting.getCode(), setting.getValue());
        }
        ArticleDO article = articleService.getArticleById(id);
        model.addAttribute("settings", map);
        model.addAttribute("cateList", cateService.listAllCate());
        model.addAttribute("article", article);
        model.addAttribute("tags", tagReferService.listNameByArticleId(article.getId()));
        model.addAttribute("author", userService.getNicknameById(article.getAuthorId()));
        //回头改
        model.addAttribute("articles",  articleMapper.listSimilarsArticle());
        model.addAttribute("similars", articleMapper.listSimilarsArticle());
        model.addAttribute("login", WebUtils.toMap(getUser()));
        CommentQuery query = new CommentQuery();
        query.setLimit(10);
        query.setPage(1);
        query.setArticleId(id);
        model.addAttribute("comments", commentService.listCommentByArticleId(query));
        return "frontend/article";
    }
    
    @ApiOperation(value="文章评论查询接口")
    @PostMapping("/comments")
    @ResponseBody
    public DataGridResult comments(CommentQuery query) {
        //设置默认10
        query.setLimit(10);
        return commentService.listCommentByArticleId(query);
    }
    
    @ApiOperation(value="文章点赞接口")
    @ApiImplicitParam(name = "articleId", value = "文章ID", required = true, dataType = "Long")
    @PostMapping("/approve")
    @ResponseBody
    public YYBlogResult approve(@RequestParam Long articleId) {
        return articleService.updateApproveCntById(articleId);
    }
}