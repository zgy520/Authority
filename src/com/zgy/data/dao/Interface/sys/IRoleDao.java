package com.zgy.data.dao.Interface.sys;

import com.zgy.data.dao.Interface.ICommonDao;
import com.zgy.model.Role;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月30日 下午8:20:24
*/
public interface IRoleDao extends ICommonDao<Role, Long> {
	public Role getRoleById(Long roleId);  //根据用户id获取具有列表的用户
}
