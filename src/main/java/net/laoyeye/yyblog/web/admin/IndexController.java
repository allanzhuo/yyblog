package net.laoyeye.yyblog.web.admin;

import net.laoyeye.yyblog.common.SessionParam;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.CookieUtils;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.NoteDO;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.CateService;
import net.laoyeye.yyblog.service.CommentService;
import net.laoyeye.yyblog.service.NoteService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * created by laoyeye on 2018/3/19
 */
@Controller("adminIndexController")
@RequestMapping("/management")
public class IndexController {
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CateService cateService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private NoteService noteService;

    @GetMapping("/index")
    public String index(HttpServletRequest request,Model model) {
    	UserDO user = (UserDO)SecurityUtils.getSubject().getPrincipal();
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

    @PostMapping("/simple/add/article")
    @ResponseBody
    public YYBlogResult simplePostArticle(@CookieValue(value = SessionParam.COOKIE_NAME, required = false) String token, HttpServletRequest request, ArticleDO article) {
        if (article.getContent().length() > 300) {
            return YYBlogResult.build(500, "草稿字数不宜过多！");
        }
        UserDO user = (UserDO)request.getSession().getAttribute(token);
		article.setAuthorId(user.getId());
        return articleService.saveSimpleArticle(article);
    }
   
    @PostMapping("/simple/add/note")
    @ResponseBody
    public YYBlogResult simplePostNote(NoteDO note) {
        if (note.getContent().length() > 300) {
            return YYBlogResult.build(500, "笔记字数不宜过多！");
        }
        return noteService.saveNote(note, null);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, String from) {
        request.getSession().invalidate();
        CookieUtils.deleteCookie(request, response, SessionParam.COOKIE_NAME);
        if (StringUtils.isEmpty(from)) {
            return "redirect:/";
        } else {
            return "redirect:" + SessionParam.MANAGEMENT_INDEX;
        }
    }
}
