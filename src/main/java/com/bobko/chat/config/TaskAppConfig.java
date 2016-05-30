package com.bobko.chat.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bobko.chat")
public class TaskAppConfig extends WebMvcConfigurerAdapter {
    
	private static final Logger LOG = LogManager.getLogger(TaskAppConfig.class); 
	
	
	
	public TaskAppConfig() {
		super();
		LOG.info("instantiated");
	}



	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry reg) {
		reg.addResourceHandler("/**").addResourceLocations("/");
	}

}
