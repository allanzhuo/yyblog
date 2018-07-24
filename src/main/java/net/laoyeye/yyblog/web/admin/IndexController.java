package net.laoyeye.yyblog.web.admin;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.Constant;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.NoteDO;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.CateService;
import net.laoyeye.yyblog.service.CommentService;
import net.laoyeye.yyblog.service.NoteService;
import net.laoyeye.yyblog.web.BaseController;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年3月9日
 * @website www.laoyeye.net
 */
@Controller("adminIndexController")
@RequestMapping("/management")
public class IndexController extends BaseController{
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CateService cateService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private NoteService noteService;

    @RequiresPermissions("blog:manage:index")
    @GetMapping("/index")
    public String index(Model model) {
        UserDO user = getUser();
        model.addAttribute("avatar", user.getAvatar());
        model.addAttribute("nickname", user.getNickname());
        return "management/index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("articleCnt", articleService.countAllArticle());
        model.addAttribute("noteCnt", noteService.countAllNote());
        model.addAttribute("commentCnt", commentService.countAllComment());
        model.addAttribute("latestArticle", articleService.getLatestArticle());
        model.addAttribute("latestNote", noteService.getLatestNote());
        model.addAttribute("latestComment", commentService.getLatestComment());
        model.addAttribute("cateList", cateService.listAllCate());
        return "management/home";
    }

    @Log("保存文章")
    @RequiresPermissions("blog:blog:add")
    @PostMapping("/simple/add/article")
    @ResponseBody
    public YYBlogResult simplePostArticle(ArticleDO article) {
        if (article.getContent().length() > 300) {
            return YYBlogResult.build(500, "草稿字数不宜过多！");
        }
        UserDO user = getUser();
        article.setAuthorId(user.getId());
        return articleService.saveSimpleArticle(article);
    }
   
    @Log("保存笔记")
    @RequiresPermissions("blog:note:add")
    @PostMapping("/simple/add/note")
    @ResponseBody
    public YYBlogResult simplePostNote(NoteDO note) {
        if (note.getContent().length() > 300) {
            return YYBlogResult.build(500, "笔记字数不宜过多！");
        }
        return noteService.saveNote(note, null);
    }

    @GetMapping("/logout")
    public String logout(String from) {
        logout();
        if (StringUtils.isEmpty(from)) {
            return "redirect:/";
        } else {
            return "redirect:" + Constant.MANAGEMENT_INDEX;
        }
    }
}
