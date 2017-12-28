package com.zgy.data.service.Impl.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.data.dao.Interface.IGenericDao;
import com.zgy.data.dao.Interface.sys.IUserDao;
import com.zgy.data.service.Impl.ServiceImpl;
import com.zgy.data.service.Interface.sys.IRoleService;
import com.zgy.data.service.Interface.sys.IUserService;
import com.zgy.model.Role;
import com.zgy.model.User;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月15日 上午7:29:44
*/
@Service("userService")
public class UserService extends ServiceImpl<User, Long> implements IUserService {
	@Resource(name="userDao")
	IUserDao userDao;
	@Resource(name="roleService")
	IRoleService roleService;
	@Resource(name="userDao")
	public void setDao(IGenericDao<User, Serializable> genericDao) {
		super.setDao(genericDao);
	}

	@Override
	public void executeStoredProcedure(Long userId, List<Long> listRoleIds) {
		// TODO Auto-generated method stub
		userDao.executeStoredProcedure(userId, listRoleIds);
	}

	@Override
	public void testd() {
		// TODO Auto-generated method stub
		userDao.testd();
	}

	@Override
	public void addUserBySP(User user) {
		// TODO Auto-generated method stub
		userDao.addUserBySP(user);
	}

	@Override
	public void testGraph() {
		// TODO Auto-generated method stub
		userDao.testGraph();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getRoleByUserId(Long userId) throws JsonProcessingException {
		// TODO Auto-generated method stub
		List<Role> lstRoles = userDao.getRolesByUserId(userId);
		List<Map> listMap = new ArrayList<>();
		lstRoles.stream().forEach(role->{
			Map map = new HashMap<>();
			map.put("Id", role.getId());
			listMap.add(map);
		});
		ObjectMapper wrapper = new ObjectMapper();
		
		return wrapper.writeValueAsString(listMap);
	}

	@Override
	public ResultCode updateRoleListForUser(Long userId, String roleIds) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			User user = userDao.getUserById(userId);
			user.clearRoles();
			if (roleIds!="") {
				List<Long> listRoleIds = Arrays.asList(roleIds.split(","))
		                .stream()
		                .map(String::trim)
		                .map(Long::parseLong)
		                .collect(Collectors.toList());
				listRoleIds.stream().forEach(roleId->{
					user.addRole(roleService.getRoleById(roleId));
				});
			}		
			this.Update(user);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("更新失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		
		return resultCode;
	}
}
