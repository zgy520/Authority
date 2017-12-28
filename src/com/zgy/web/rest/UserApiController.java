package com.zgy.web.rest;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zgy.data.service.Interface.sys.IUserService;
import com.zgy.model.User;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月6日 下午10:58:38
*/
@Controller
@RequestMapping("/uusers")
public class UserApiController {
	@Resource(name="userService")
	private IUserService userServiceRepository; 
	
	@RequestMapping(method=RequestMethod.GET)
	public List<User> users(){
		return userServiceRepository.findAll();
	}
}
