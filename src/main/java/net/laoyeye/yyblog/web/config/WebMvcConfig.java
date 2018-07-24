package net.laoyeye.yyblog.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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

}