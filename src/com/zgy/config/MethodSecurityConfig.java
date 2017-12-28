package com.zgy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月26日 下午1:19:11
*/
@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled=true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
}
