//package net.laoyeye.yyblog.web.interceptor;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import net.laoyeye.yyblog.common.SessionParam;
//import net.laoyeye.yyblog.common.utils.WebUtils;
//import net.laoyeye.yyblog.common.utils.CookieUtils;
//import net.laoyeye.yyblog.model.UserDO;
//
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 前台登陆校验拦截器
// * @author 小卖铺的老爷爷
// * @date 2018年2月24日
// * @website www.laoyeye.net
// */
//public class ValidateInterceptor extends HandlerInterceptorAdapter {
//	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
//	
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//    	String sessionId = CookieUtils.getCookieValue(request, SessionParam.COOKIE_NAME);
//    	if (StringUtils.isNotEmpty(sessionId)) {
//			UserDO user = (UserDO)request.getSession().getAttribute(sessionId);
//			if (user != null) {
//				 if (modelAndView != null)
//	                    modelAndView.getModelMap().addAttribute("login", WebUtils.toMap(user));
//			}
//		}
//
//    }
//}
//
