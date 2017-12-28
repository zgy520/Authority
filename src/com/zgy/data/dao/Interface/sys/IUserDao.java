package com.zgy.data.dao.Interface.sys;

import java.util.List;

import com.zgy.data.dao.Interface.ICommonDao;
import com.zgy.model.Role;
import com.zgy.model.User;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月13日 下午8:38:53
*/
public interface IUserDao extends ICommonDao<User,Long> {
	public List<User> getAllUser();
	public void executeStoredProcedure(Long userId,List<Long> listRoleIds);  //执行存储过程
	public void testd();
	public void addUserBySP(User user);
	public void testGraph();
	public List<Role> getRolesByUserId(Long userId);
	public User getUserById(Long userId);  //根据用户id获取具有列表的用户
	
	public User getUserForOriginations(Long userId); //根据用户的id返回可以访问所有组织机构的用户
}
