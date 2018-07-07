//package net.laoyeye.yyblog.web.interceptor;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import net.laoyeye.yyblog.common.SessionParam;
//import net.laoyeye.yyblog.common.utils.WebUtils;
//import net.laoyeye.yyblog.common.utils.CookieUtils;
//import net.laoyeye.yyblog.model.UserDO;
//import net.laoyeye.yyblog.service.UserService;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * session拦截器
// * @author 小卖铺的老爷爷
// * @date 2018年2月24日
// * @website www.laoyeye.net
// */
//public class SessionInterceptor extends HandlerInterceptorAdapter {
//	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		String sessionId = CookieUtils.getCookieValue(request, SessionParam.COOKIE_NAME);
//		if (StringUtils.isNotEmpty(sessionId)) {
//			UserDO user = (UserDO)request.getSession().getAttribute(sessionId);
//			if (user == null) {
//				UserDO cookieUser = userService.getUserByToken(sessionId);
//				if (cookieUser != null) {
//					request.getSession().setAttribute(sessionId, cookieUser);
//					logger.info("[已登录用户]");
//					return true;
//				}
//				logger.info("[未登录用户]");
//				response.sendRedirect(SessionParam.LOGIN_URL);
//				return false;
//			} else {
//				logger.info("[已登录用户]");
//				return true;
//			}
//		} else {
//			response.sendRedirect(SessionParam.LOGIN_URL);
//			return false;
//		}
//	}
//	
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//    	String sessionId = CookieUtils.getCookieValue(request, SessionParam.COOKIE_NAME);
//    	if (StringUtils.isNotEmpty(sessionId)) {
//			UserDO user = (UserDO)request.getSession().getAttribute(sessionId);
//			if (user == null) {
//				 if (modelAndView != null)
//	                    modelAndView.getModelMap().addAttribute("login", WebUtils.toMap(user));
//			}
//		} else {
//			try {
//				response.sendRedirect(SessionParam.LOGIN_URL);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//    }
//}
//
