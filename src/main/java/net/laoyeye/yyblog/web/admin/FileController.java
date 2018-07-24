package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.laoyeye.yyblog.annotation.Log;

@Controller
@RequestMapping("/management/file")
public class FileController {

    @Log("打开资源管理页面")
    @RequiresPermissions("blog:file:index")
    @RequestMapping
    public String file() {
        return "management/file";
    }

}
