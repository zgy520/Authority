package com.zgy.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月27日 下午10:58:38
*/

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages="com.zgy")
public class AopConfig {
	public AopConfig() {
		//System.out.println("AopConfigure");
	}
}
