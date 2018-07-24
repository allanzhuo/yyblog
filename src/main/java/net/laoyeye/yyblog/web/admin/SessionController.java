package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.DataGridResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.service.SessionService;

@RequestMapping("/management/online")
@Controller
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @Log("在线用户")
    @RequiresPermissions("sys:online:index")
    @GetMapping
    public String online() {
        return "management/online";
    }

    @ResponseBody
    @RequestMapping("/list")
    public DataGridResult list(int page,int limit,String username) {
        return sessionService.list(page,limit,username);
    }

    @Log("强制退出")
    @RequiresPermissions("sys:online:forceout")
    @ResponseBody
    @RequestMapping("/remove/{sessionId}")
    public YYBlogResult forceLogout(@PathVariable("sessionId") String sessionId) {
        try {
            sessionService.removeUser(sessionId);
            return YYBlogResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return YYBlogResult.build(500, "强制退出失败！请联系管理员。");
        }

    }

}
