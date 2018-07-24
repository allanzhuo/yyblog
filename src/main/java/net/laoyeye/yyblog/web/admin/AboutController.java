package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.AboutDO;
import net.laoyeye.yyblog.service.AboutService;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月20日
 * @website www.laoyeye.net
 */
@Controller("adminAboutontroller")
@RequestMapping("/management/about")
public class AboutController {
    @Autowired
    private AboutService aboutService;

    @Log("进入关于内容页面")
    @RequiresPermissions("blog:about:index")
    @GetMapping
    public String about(Model model) {
        model.addAttribute("me", aboutService.getAboutByTab("about_me"));
        model.addAttribute("website", aboutService.getAboutByTab("about_website"));
        model.addAttribute("blog", aboutService.getAboutByTab("about_blog"));
        return "management/about";
    }

    @Log("进入关于老爷爷页面")
    @GetMapping("/blog")
    public String index() {

        return "management/aboutblog";
    }

    @Log("修改关于信息")
    @RequiresPermissions("blog:about:update")
    @PostMapping("/update")
    @ResponseBody
    public YYBlogResult update(AboutDO about) {
        
        return aboutService.updateByTab(about);
    }
}
