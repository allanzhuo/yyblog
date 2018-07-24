package net.laoyeye.yyblog.web.admin;

import java.util.Arrays;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.NKBlogResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.ArticleDO;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.model.query.ArticleQuery;
import net.laoyeye.yyblog.service.ArticleService;
import net.laoyeye.yyblog.service.CateService;
import net.laoyeye.yyblog.service.TagReferService;
import net.laoyeye.yyblog.service.UploadService;
import net.laoyeye.yyblog.web.BaseController;
/**
 * 文章管理
 * @author 小卖铺的老爷爷
 * @date 2018年7月7日
 * @website www.laoyeye.net
 */
@Controller("adminArticleController")
@RequestMapping("/management/blog")
public class ArticleController extends BaseController{
    @Autowired
    private CateService cateService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagReferService tagReferService;
    @Autowired
    private UploadService uploadService;

    @Log("打开文章页面")
    @RequiresPermissions("blog:blog:index")
    @GetMapping
    public String blog(Model model) {
        model.addAttribute("cateList", cateService.listAllCate());
        return "management/blog";
    }
    
    @Log("打开文章列表页面")
    @RequiresPermissions("blog:blogs:index")
    @GetMapping("/index")
    public String index() {
        return "management/blogs";
    }
    
    @Log("保存文章")
    @RequiresPermissions("blog:blog:add")
    @PostMapping("/add")
    @ResponseBody
    public YYBlogResult add(ArticleDO article, String tagName) {
        UserDO user = getUser();
        article.setAuthorId(user.getId());
        return articleService.saveArticle(article, tagName);
    }
    
    @Log("查询文章列表")
    @RequestMapping("/list")
    @ResponseBody
    public DataGridResult listArticle(ArticleQuery query) {
        DataGridResult result = articleService.listPageArticle(query);
        return result;
    }

    @Log("打赏状态修改")
    @RequiresPermissions("blog:blogs:appreciable")
    @PostMapping("/edit/appreciable/{id}")
    @ResponseBody
    public YYBlogResult appreciable(@PathVariable("id") Long id, Boolean appreciable) {

        return articleService.updateAppreciableById(id, appreciable);
    }
    @Log("评论状态修改")
    @RequiresPermissions("blog:blogs:commented")
    @PostMapping("/edit/commented/{id}")
    @ResponseBody
    public YYBlogResult commented(@PathVariable("id") Long id, Boolean commented) {

        return articleService.updateCommentedById(id, commented);
    }

    @Log("置顶状态修改")
    @RequiresPermissions("blog:blogs:top")
    @PostMapping("/edit/top/{id}")
    @ResponseBody
    public YYBlogResult top(@PathVariable("id") Long id, Boolean top) {

        return articleService.updateTopById(id, top);
    }
    
    @Log("进入编辑文章页面")
    @RequiresPermissions("blog:blogs:editIndex")
    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("cateList", cateService.listAllCate());
        model.addAttribute("editArticle", articleService.getArticleById(id));
        String[] tagArray = tagReferService.listNameByArticleId(id).toArray(new String[]{});
        String tags = Arrays.toString(tagArray);
        model.addAttribute("articleTags", tags.substring(1, tags.length() - 1));
        return "management/blog_edit";
    }

    @Log("编辑文章提交")
    @RequiresPermissions("blog:blog:edit")
    @PostMapping("/doEdit")
    @ResponseBody
    public YYBlogResult doEdit(ArticleDO article, String tagName) {
        UserDO user = getUser();
        article.setAuthorId(user.getId());
        return articleService.updateArticle(article, tagName);
    }
    
    @Log("删除文章")
    @RequiresPermissions("blog:blogs:delete")
    @PostMapping("/delete/{id}")
    @ResponseBody
    public YYBlogResult delete(@PathVariable("id") Long id) {
        return articleService.delete(id);
    }

    @Log("上传文章封面")
    @RequiresPermissions("blog:blog:cover")
    @PostMapping("/upload/cover")
    @ResponseBody
    public YYBlogResult uploadCover(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            return uploadService.upload(file);
        } else {
            return YYBlogResult.build(500, "上传文件为空！");
        }
    }

    @Log("上传文章图片")
    @RequiresPermissions("blog:blog:upload")
    @PostMapping("/upload")
    @ResponseBody
    public NKBlogResult upload(@RequestParam(value = "uploadFile", required = false) MultipartFile file) {
        if (file != null) {
            return uploadService.uploadNK(file);
        } else {
            return NKBlogResult.build(001, "上传文件为空！");
        }
    }

}
