package com.zgy.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月19日 下午3:53:30
*/
@Configuration
@EnableCaching
public class CachingConfig {
	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cm) {
		return new EhCacheCacheManager(cm);
	}
	@Bean
	public EhCacheManagerFactoryBean ehcache() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}
}
