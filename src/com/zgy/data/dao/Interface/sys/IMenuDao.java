package com.zgy.data.dao.Interface.sys;

import com.zgy.data.dao.Interface.ICommonDao;
import com.zgy.model.Menu;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:30:38
*/
public interface IMenuDao extends ICommonDao<Menu, Long> {
	public Menu getMenuForBtns(Long menuId);  //根据菜单id返回需要获取按钮的菜单
}
