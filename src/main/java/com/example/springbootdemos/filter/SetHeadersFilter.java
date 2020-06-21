package com.example.springbootdemos.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Configuration
public class SetHeadersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
        ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;
        SetHeadersServletRequestWrapper mutableRequest = new SetHeadersServletRequestWrapper(req);
        mutableRequest.putHeader("mi-cabecera", "mi-valor");
        chain.doFilter(mutableRequest, response);
    }

    @Bean
    public FilterRegistrationBean<SetHeadersFilter> loggingFilter(){
        FilterRegistrationBean<SetHeadersFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SetHeadersFilter());
        registrationBean.addUrlPatterns("/demo/rest/consumer/*");

        return registrationBean;
    }
}
