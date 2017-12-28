package com.zgy.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.zgy.filters.EncodingFilter;


public class AuthorityWebInitializer 
	extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {RootConfig.class,DataConfig.class,CachingConfig.class,
								SecurityConfig.class,MethodSecurityConfig.class,
								AopConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class<?>[] {WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	@Override
	protected Filter[] getServletFilters() {   //注册过滤器
		return new Filter[] {new EncodingFilter()};
	}

}
