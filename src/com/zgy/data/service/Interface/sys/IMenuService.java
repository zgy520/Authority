package com.zgy.data.service.Interface.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.data.service.Interface.ICommonService;
import com.zgy.model.Menu;
import com.zgy.model.Pagination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:35:10
*/
public interface IMenuService extends ICommonService<Menu, Long> {
	public String getAllMenuByPagination(Pagination pagination) throws JsonProcessingException;
	public String getAllMenus() throws JsonProcessingException;
	public String addMenu(Menu menu);
	public String updateMenu(Menu menu);
	public String deleteMenu(Long Id);
	public String getAllMenusIdText() throws JsonProcessingException;
	public String updateBtnListForMenu(Long Id,String commonIdList);
	public String getBtnsByMenuId(Long Id);
}
