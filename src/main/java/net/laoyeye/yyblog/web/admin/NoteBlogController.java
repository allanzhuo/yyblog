package net.laoyeye.yyblog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月26日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/management/noteblog")
public class NoteBlogController {

    @GetMapping
    public String index() {
        return "management/noteblog";
    }
}
