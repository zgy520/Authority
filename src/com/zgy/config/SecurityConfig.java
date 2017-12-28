package com.zgy.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zgy.data.service.Interface.sys.IUserService;
import com.zgy.security.CustomerUserService;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月19日 下午6:22:06
*/
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	DataSource dataSource;
	@Autowired
	IUserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.formLogin()
			//.loginPage("/login")
		/*	.and()
			.rememberMe()
			.tokenValiditySeconds(2419200)
			.key("authorityKey")
			.and()
			.httpBasic()
			.realmName("Authority")*/
			.and()
			.authorizeRequests()
			.antMatchers("/users").access("hasRole('ROLE_USER')")
			.antMatchers("/users/*").access("hasRole('ROLE_USER')")
			.anyRequest().permitAll()
			.and()
			.csrf().disable()
			.headers().frameOptions().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		/*auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select loginName,userPassword,true from tuser where loginName=?")
			.authoritiesByUsernameQuery("select loginName,'ROLE_USER','ROLE_ADMIN' from tuser where loginName=?")
			.passwordEncoder(bCryptPasswordEncoder);*/
		auth.userDetailsService(new CustomerUserService(userService))
			.passwordEncoder(bCryptPasswordEncoder);
	}
}
