package com.cyz.SpringBootFirst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; //打包成war需要继承这个类，然后重写configure方法
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties
@EnableCaching
public class SpringBootFirstApplication {

	public static void main(String[] args) {
		//关闭热部署 方式1
		//System.setProperty("spring.devtools.restart.enabled","false");
		SpringApplication.run(SpringBootFirstApplication.class, args);
	}
	
}
