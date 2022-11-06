package com.DemoSpring.Security;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class ConfigError implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/403").setViewName("403");
		registry.addViewController("/login").setViewName("login");
//		registry.addViewController("/login.html").setViewName("login");
//		  registry.addViewController("/login").setViewName("login");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		Path uploadDirPath = Paths.get("./images");
		String uploadPath = uploadDirPath.toFile().getAbsolutePath();
		registry.addResourceHandler("/images/**").addResourceLocations("file:/" + uploadPath +"/" );
	}
}
