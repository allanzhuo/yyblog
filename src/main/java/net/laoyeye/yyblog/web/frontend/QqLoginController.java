package net.laoyeye.yyblog.web.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.service.QqLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * QQ登录
 * @author 小卖铺的老爷爷
 * @date 2018年3月9日
 * @website www.laoyeye.net
 */
@Controller
@RequestMapping("/api")
public class QqLoginController {

	@Autowired
	private QqLoginService qqLoginService;

	@GetMapping("/qq")
	public String qqLogin(HttpServletRequest request) {
		String authorizeUrl = null;
		try {
			authorizeUrl = new Oauth().getAuthorizeURL(request);
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return "redirect:" + authorizeUrl;
	}

	@GetMapping("/qq/callback")
	public String qqCallback(HttpServletRequest request, HttpServletResponse response) {	
		YYBlogResult result = qqLoginService.login(request, response);
		if (result.getCode() == 200) {
			return "redirect:/index";
		}
		 return "error/page";
	}
}
