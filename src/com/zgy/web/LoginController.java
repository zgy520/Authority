package com.zgy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月25日 下午10:11:48
*/
@Controller

public class LoginController {
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String loginIn() {
		System.out.println("执行到此了");
		return "redirect:users";
	}
}
