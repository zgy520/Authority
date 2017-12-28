package com.zgy.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zgy.data.service.Interface.sys.IUserService;
import com.zgy.model.User;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月19日 下午9:11:29
*/
public class CustomerUserService implements UserDetailsService {

	private final IUserService userService;
	
	public CustomerUserService(IUserService userService) {
		// TODO Auto-generated constructor stub
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userService.getObjByNonId("loginName", username).get(0);
		if (user!=null) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getUserPassword(), authorities);
		}
		throw new UsernameNotFoundException("User "+username+" not foundQ!");
	}

}
