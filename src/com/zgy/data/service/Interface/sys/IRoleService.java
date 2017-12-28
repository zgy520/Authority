package com.zgy.data.service.Interface.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.data.service.Interface.ICommonService;
import com.zgy.model.Role;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月30日 下午8:16:55
*/
public interface IRoleService extends ICommonService<Role, Long> {
	public Role getRoleById(Long roleId);  //根据用户id获取具有列表的用户
	public String getUsersByRoleId(Long roleId) throws JsonProcessingException;  //根据角色id获取用户列表
	public String updateUserListForRole(Long Id,String commonIdList);  //为角色关联用户
}
