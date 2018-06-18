package net.laoyeye.yyblog.web.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import net.laoyeye.yyblog.common.SessionParam;
import net.laoyeye.yyblog.common.utils.CookieUtils;
import net.laoyeye.yyblog.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台登录拦截器
 * @author 小卖铺的老爷爷
 * @date 2018年1月24日
 * @website www.laoyeye.net
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//从cookie中取token
		String token = CookieUtils.getCookieValue(request, SessionParam.COOKIE_NAME);
		if (StringUtils.isNotBlank(token)) {
			boolean check = userService.checkUserByToken(token);
			if (!check) {
				response.sendRedirect("/");
				return false;
			}
			return true;
		} else {
			response.sendRedirect("/login");
			return false;
		}
	}

}
