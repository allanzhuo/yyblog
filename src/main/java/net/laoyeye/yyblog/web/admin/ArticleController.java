package net.laoyeye.yyblog.web.admin;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.laoyeye.yyblog.annotation.LogAnno;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.NKBlogResult;
import net.laoyeye.yyblog.common.SessionParam;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.IDUtils;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.ArticleQuery;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.CateService;
import net.laoyeye.yyblog.service.TagReferService;
import net.laoyeye.yyblog.service.UploadService;
/**
 * created by laoyeye on 2018/1/14 at 15:18
 */
@Controller("adminArticleController")
@RequestMapping("/management/blog")
public class ArticleController {
	@Autowired
	private CateService cateService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagReferService tagReferService;
	@Autowired
	private UploadService uploadService;

	@GetMapping
	public String blog(Model model) {
		model.addAttribute("cateList", cateService.listAllCate());
		return "management/blog";
	}

	@GetMapping("/index")
	public String index() {
		return "management/blogs";
	}
	
	@PostMapping("/add")
	@ResponseBody
	public YYBlogResult add(@CookieValue(value = SessionParam.COOKIE_NAME, required = false) String token, HttpServletRequest request, ArticleDO article, String tagName) {
		UserDO user = (UserDO)request.getSession().getAttribute(token);
		article.setAuthorId(user.getId());
		return articleService.saveArticle(article, tagName);
	}
	
	@LogAnno("查询文章列表")
	/*@RequiresPermissions("sys:article:list")*/
	@RequestMapping("/list")
	@ResponseBody
	public DataGridResult listArticle(ArticleQuery query) {
		DataGridResult result = articleService.listPageArticle(query);
		return result;
	}

	@PostMapping("/edit/appreciable/{id}")
	@ResponseBody
	public YYBlogResult appreciable(@PathVariable("id") Long id, Boolean appreciable) {

		return articleService.updateAppreciableById(id, appreciable);
	}
	@LogAnno("评论状态修改")
	@PostMapping("/edit/commented/{id}")
	@ResponseBody
	public YYBlogResult commented(@PathVariable("id") Long id, Boolean commented) {

		return articleService.updateCommentedById(id, commented);
	}

	@PostMapping("/edit/top/{id}")
	@ResponseBody
	public YYBlogResult top(@PathVariable("id") Long id, Boolean top) {

		return articleService.updateTopById(id, top);
	}
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") long id) {
		model.addAttribute("cateList", cateService.listAllCate());
		model.addAttribute("editArticle", articleService.getArticleById(id));
		String[] tagArray = tagReferService.listNameByArticleId(id).toArray(new String[]{});
		String tags = Arrays.toString(tagArray);
		model.addAttribute("articleTags", tags.substring(1, tags.length() - 1));
		return "management/blog_edit";
	}

	@PostMapping("/doEdit")
	@ResponseBody
	public YYBlogResult doEdit(@CookieValue(value = SessionParam.COOKIE_NAME, required = false) String token, HttpServletRequest request, ArticleDO article, String tagName) {
		UserDO user = (UserDO)request.getSession().getAttribute(token);
		article.setAuthorId(user.getId());
		return articleService.updateArticle(article, tagName);
	}

	@PostMapping("/delete/{id}")
	@ResponseBody
	public YYBlogResult delete(@PathVariable("id") Long id) {
		return articleService.delete(id);
	}

	@PostMapping("/upload/cover")
	@ResponseBody
	public YYBlogResult uploadCover(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
		if (file != null) {
			return uploadService.upload(file);
		} else {
			return YYBlogResult.build(500, "上传文件为空！");
		}
	}

	@PostMapping("/upload")
	@ResponseBody
	public NKBlogResult upload(HttpServletRequest request, @RequestParam(value = "uploadFile", required = false) MultipartFile file) {
		if (file != null) {
			return uploadService.uploadNK(file);
		} else {
			return NKBlogResult.build(001, "上传文件为空！");
		}
	}

}
