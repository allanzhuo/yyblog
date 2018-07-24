package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.enums.ResultEnum;
import net.laoyeye.yyblog.model.UserDO;
import net.laoyeye.yyblog.web.BaseController;
/**
 * 
 * @author 小卖铺的老爷爷
 * @date 2018年1月14日
 * @website www.laoyeye.net
 */
@Controller
public class LoginController extends BaseController{

    @GetMapping("/login")
    public String login() {
        //判断是记住登陆还是授权登陆
        //boolean authenticated = getSubjct().isAuthenticated();
        UserDO user = getUser();
        if (user != null) {
            return "redirect:management/index";
        }
        return "management/login";
    }
    
    @Log("登陆验证")
    @PostMapping("/login")
    @ResponseBody
    public YYBlogResult login(String username, String password, Boolean rememberMe) {
        if (rememberMe == null) {
            rememberMe = false;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (Exception e) {
            return YYBlogResult.build(ResultEnum.UNKONW_ERROR.getCode(), e.getMessage());
        }
        return YYBlogResult.ok();
    }
}
