package net.laoyeye.yyblog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.model.About;
import net.laoyeye.yyblog.service.AboutService;

/**
 * created by laoyeye on 2018/1/20 at 16:33
 */
@Controller("adminAboutontroller")
@RequestMapping("/management/about")
public class AboutController {
	@Autowired
	private AboutService aboutService;

    @GetMapping
    public String about(Model model) {
        model.addAttribute("me", aboutService.getAboutByTab("about_me"));
        model.addAttribute("website", aboutService.getAboutByTab("about_website"));
        model.addAttribute("blog", aboutService.getAboutByTab("about_blog"));
        return "management/about";
    }

    @GetMapping("/blog")
    public String index() {

        return "management/aboutblog";
    }

    @PostMapping("/update")
    @ResponseBody
    public YYBlogResult update(About about) {
    	
    	return aboutService.updateByTab(about);
    }
}
