package com.example.springbootdemos.rest.simpleclient;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonPlaceholderControllerTestConfiguration {

	@Bean 
    ServletWebServerFactory servletWebServerFactory() {
	  return new TomcatServletWebServerFactory();
	}
	
}
