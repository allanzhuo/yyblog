package net.laoyeye.yyblog.web.admin;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.laoyeye.yyblog.annotation.Log;
import net.laoyeye.yyblog.common.SessionParam;
import net.laoyeye.yyblog.common.YYBlogResult;


/**
 * created by laoyeye on 2018/1/14 at 12:47
 */
@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@CookieValue(value = SessionParam.COOKIE_NAME, required = false) String token) {
//		if (StringUtils.isEmpty(token)) {
//            return "management/login";
//        }
//
//        if (StringUtils.isNotEmpty(token)) {
//        	boolean check = userService.checkUserByToken(token);
//        	if (check) {
//        		return "redirmanagement/index";
//			}
//            return "/";
//        }
        return "management/login";
	}
	
	@Log("登陆验证")
	@PostMapping("/login")
	@ResponseBody
	public YYBlogResult login(String username, String password, Boolean remember) {
		if (remember == null) {
			remember = false;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			/*token.setRememberMe(remember);*/
			subject.login(token);
		} catch (Exception e) {
			return YYBlogResult.build(403, e.getMessage());
		}
		return YYBlogResult.ok();
	}
}
