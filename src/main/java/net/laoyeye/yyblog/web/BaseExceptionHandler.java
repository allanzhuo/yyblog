package net.laoyeye.yyblog.web;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.AjaxUtils;
import net.laoyeye.yyblog.enums.ResultEnum;
import net.laoyeye.yyblog.service.LogService;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BaseExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	LogService logService;

	@ExceptionHandler(AuthorizationException.class)
	public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
		logger.error(e.getMessage(), e);
		if (AjaxUtils.jsAjax(request)) {
			return YYBlogResult.build(ResultEnum.UN_AUTHORIZED.getCode(), ResultEnum.UN_AUTHORIZED.getValue());
		}
		return new ModelAndView("error/403");
	}


	@ExceptionHandler({Exception.class})
	public Object handleException(Exception e, HttpServletRequest request) {
		logger.error(e.getMessage(), e);
		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			if (AjaxUtils.jsAjax(request)) {
				return YYBlogResult.build(ResultEnum.NOT_FOUND.getCode(), ResultEnum.NOT_FOUND.getValue());
			}
			return new ModelAndView("error/404");
		} else {
			if (AjaxUtils.jsAjax(request)) {
				return YYBlogResult.build(ResultEnum.UNKONW_ERROR.getCode(), ResultEnum.UNKONW_ERROR.getValue());
			}
			return new ModelAndView("error/500");
		}
	}
}
