package com.zgy.data.dao.Interface.sys;

import com.zgy.data.dao.Interface.ICommonDao;
import com.zgy.model.Origination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:31:06
*/
public interface IOriginationDao extends ICommonDao<Origination, Long> {
	public Origination getOrgForUserByOrgId(Long orgId);
}
