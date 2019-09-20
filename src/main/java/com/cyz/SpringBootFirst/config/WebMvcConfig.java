package com.cyz.SpringBootFirst.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 *
 以下WebMvcConfigurerAdapter 比较常用的重写接口
解决跨域问题 
public void addCorsMappings(CorsRegistry registry) ;
添加拦截器 
//    void addInterceptors(InterceptorRegistry registry);
这里配置视图解析器 
视图跳转控制器 
void addViewControllers(ViewControllerRegistry registry);
void configureViewResolvers(ViewResolverRegistry registry);
配置内容裁决的一些选项 
   void configureContentNegotiation(ContentNegotiationConfigurer configurer);
 视图跳转控制器
    void addViewControllers(ViewControllerRegistry registry);
    静态资源处理
  void addResourceHandlers(ResourceHandlerRegistry registry);
   默认静态资源处理器 
 * @author cyz
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
	
	@Value("${owner.path}")
	private String filePath;
	
	@Value("${owner.filePattern}")
	private String filePattern;

	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		System.out.println("配置CROS");
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET","POST","PUT","DELETE","OPTIONS","PATCH")
		.allowCredentials(true).allowedHeaders("Content-Type","X-user","X-token").maxAge(3600);
		super.addCorsMappings(registry);
	}

	/*@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(filePattern).addResourceLocations(filePath);//最后的那个绝对路径结尾必须加上/
		super.addResourceHandlers(registry);
	}*/

	
	
}
