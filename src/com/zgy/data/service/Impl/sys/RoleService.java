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
import com.zgy.data.dao.Interface.sys.IRoleDao;
import com.zgy.data.dao.Interface.sys.IUserDao;
import com.zgy.data.service.Impl.ServiceImpl;
import com.zgy.data.service.Interface.sys.IRoleService;
import com.zgy.model.Role;
import com.zgy.model.User;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月30日 下午8:18:16
*/
@Service("roleService")
public class RoleService extends ServiceImpl<Role, Long> implements IRoleService {
	@Resource(name="roleDao")
	IRoleDao roleDao;
	@Resource(name="userDao")
	IUserDao userDao;
	
	@Resource(name="roleDao")
	public void setDao(IGenericDao<Role, Serializable> genericDao) {
		super.setDao(genericDao);
	}

	@Override
	public Role getRoleById(Long roleId) {
		// TODO Auto-generated method stub
		return roleDao.getRoleById(roleId);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getUsersByRoleId(Long roleId) throws JsonProcessingException {
		// TODO Auto-generated method stub
		List<User> listUsers = roleDao.getRoleById(roleId).getColUsers().stream().collect(Collectors.toList());
		List<Map> listMap = new ArrayList<>();
		listUsers.stream().forEach(user->{
			Map map = new HashMap<>();
			map.put("Id", user.getId());
			listMap.add(map);
		});
		ObjectMapper wrapper = new ObjectMapper();
		return wrapper.writeValueAsString(listMap);
	}

	@Override
	public String updateUserListForRole(Long Id, String commonIdList) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Role role = roleDao.getRoleById(Id);
			role.clearUser();
			
			if (commonIdList!="") {
				List<Long> listUserIds = Arrays.asList(commonIdList.split(","))
		                .stream()
		                .map(String::trim)
		                .map(Long::parseLong)
		                .collect(Collectors.toList());
				listUserIds.stream().forEach(userId->{
					role.addUser(userDao.getUserById(userId));
				});
			}		
			this.Update(role);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("关联用户失败");
			resultCode.setDetailMsg(e.getMessage());
		}		
		
		return ResultCode.getJsonString(resultCode);
	}

	
}
