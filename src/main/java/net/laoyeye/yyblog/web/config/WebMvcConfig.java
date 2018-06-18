package net.laoyeye.yyblog.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.laoyeye.yyblog.web.interceptor.AdminInterceptor;
import net.laoyeye.yyblog.web.interceptor.SessionInterceptor;
import net.laoyeye.yyblog.web.interceptor.ValidateInterceptor;

/**
 * 配置文件设置
 * @author 小卖铺的老爷爷
 * @date 2018年4月24日
 * @website www.laoyeye.net
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private final Environment environment;

	@Autowired
	public WebMvcConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public AdminInterceptor getAdminInterceptor(){
		return new AdminInterceptor();
	}

	@Bean
	public SessionInterceptor getSessionInterceptor(){
		return new SessionInterceptor();
	}

	@Bean
	public ValidateInterceptor getValidateInterceptor(){
		return new ValidateInterceptor();
	}  

	/**
	 * 配置静态资源路径以及上传文件的路径
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/upload/**").addResourceLocations(environment.getProperty("spring.resources.static-locations"));
		/*swagger-ui*/
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	/**
	 * 配置后台以及前台的一些拦截
	 *
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getValidateInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(getSessionInterceptor()).addPathPatterns("/management/**", "/token/**");
		registry.addInterceptor(getAdminInterceptor()).addPathPatterns("/management/**");
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		/*return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error?errorCode=404"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error?errorCode=500"));
            container.addErrorPages(new ErrorPage(Throwable.class, "/error?errorCode=500"));
        };*/
		return new EmbeddedServletContainerCustomizer() { 
			@Override 
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404")); 
				container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/404")); 
				container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500")); 
				container.addErrorPages(new ErrorPage(java.lang.Throwable.class, "/error/500"));
			}
		};
	}


}