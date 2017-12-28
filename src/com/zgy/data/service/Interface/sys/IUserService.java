package com.zgy.data.service.Interface.sys;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.data.service.Interface.ICommonService;
import com.zgy.model.User;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月15日 上午7:29:10
*/
public interface IUserService extends ICommonService<User, Long> {
	public void executeStoredProcedure(Long userId,List<Long> listRoleIds);
	public void testd();
	public void addUserBySP(User user);
	public void testGraph();
	public String getRoleByUserId(Long userId) throws JsonProcessingException;  //根据用户获取该用户下的所有角色
	public ResultCode updateRoleListForUser(Long userId,String roleIds);  //更新用户所对应的角色列表
	
}
