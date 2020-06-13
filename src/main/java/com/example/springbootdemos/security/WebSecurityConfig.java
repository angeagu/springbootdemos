package com.example.springbootdemos.security;

import org.apache.http.protocol.HTTP;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //COn formulario de autenticacion
                /*http.csrf().disable().authorizeRequests()
                .antMatchers("/demo/basicrest/**").authenticated()
                .and().formLogin().usernameParameter("angel").passwordParameter("aaguado");*/
        //Con HTTP Basic
        http.csrf().disable().authorizeRequests()
                .antMatchers("/demo/basicrest/**").authenticated()
                .and().httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUserDetails(new com.example.springbootdemos.security.User()).build();
        return new InMemoryUserDetailsManager(user);
    }
}
