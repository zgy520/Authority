package com.zgy.web.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.data.service.Interface.sys.IMenuService;
import com.zgy.model.Menu;
import com.zgy.model.Pagination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:40:48
*/
@Controller
@RequestMapping(value="menus",produces = "text/html;charset=UTF-8")
public class MenuController {

	@Resource(name="menuService")
	IMenuService menuService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showMenuPage() {
		return "users/menu";
	}
	@RequestMapping(value="getAllMenuByPagination")
	@ResponseBody
	public String getAllMenuByPagination(Pagination pagination) throws JsonProcessingException{
		return menuService.getAllMenuByPagination(pagination);
	}
	@RequestMapping(value="getAllMenus")
	@ResponseBody
	public String getAllMenus() throws JsonProcessingException{
		return menuService.getAllMenus();
	}
	@RequestMapping(value="getAllMenusIdText")
	@ResponseBody
	public String getAllMenusIdText() throws JsonProcessingException{
		return menuService.getAllMenusIdText();
	}
	@RequestMapping(value="addMenu")
	@ResponseBody
	public String addMenu(Menu menu) {
		return menuService.addMenu(menu);
	}
	@RequestMapping(value="updateMenu")
	@ResponseBody
	public String updateMenu(Menu menu) {
		return menuService.updateMenu(menu);
	}
	@RequestMapping(value="deleteMenu")
	@ResponseBody
	public String deleteMenu(Long Id) {
		return menuService.deleteMenu(Id);
	}
	@RequestMapping(value="updateBtnListForMenu")
	@ResponseBody
	public String updateBtnListForMenu(Long Id,String commonIdList) {
		return menuService.updateBtnListForMenu(Id, commonIdList);
	}
	@RequestMapping(value="getBtnsByMenuId")
	@ResponseBody
	public String getBtnsByMenuId(Long Id) {
		return menuService.getBtnsByMenuId(Id);
	}
}
