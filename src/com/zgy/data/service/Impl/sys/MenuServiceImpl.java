package com.zgy.data.service.Impl.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.hibernate.engine.jdbc.batch.internal.BatchingBatch;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.data.dao.Interface.IGenericDao;
import com.zgy.data.dao.Interface.sys.IButtonDao;
import com.zgy.data.dao.Interface.sys.IMenuDao;
import com.zgy.data.service.Impl.ServiceImpl;
import com.zgy.data.service.Interface.sys.IMenuService;
import com.zgy.model.Button;
import com.zgy.model.Menu;
import com.zgy.model.Pagination;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:36:36
*/
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<Menu, Long> implements IMenuService {
	@Resource(name="menuDao")
	private IMenuDao menuDao;
	@Resource(name="btnDao")
	private IButtonDao btnDao;
	
	@Resource(name="menuDao")
	public void setDao(IGenericDao<Menu, Serializable> genericDao) {
		super.setDao(genericDao);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getJsonMenuObj(List<Menu> listMenus) throws JsonProcessingException{
		List<Map> listMap = new ArrayList<>();
		ObjectMapper wrapper = new ObjectMapper();
		if (listMenus!=null) {
			listMenus.stream().forEach(menu->{
				Map map = getMapByMenu(menu);
				if (menu.getSupMenuId()==null) {
					map.put("children", getChildMapByMenu(menu));
					listMap.add(map);
				}				
			});
		}
		return wrapper.writeValueAsString(listMap);
	}
	/**
	 * 获取当前部门的子部门
	 * @param org
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<Map> getChildMapByMenu(Menu menu){
		List<Menu> listMenus = this.getObjByNonId("supMenuId", String.valueOf(menu.getId()));
		List<Map> listMaps = new ArrayList<>();
		if (listMenus.size()>0) {
			listMenus.stream().forEach(item->{
				Map map = getMapByMenu(item);
				map.put("children", getChildMapByMenu(item));
				listMaps.add(map);
			});
		}
		return listMaps;
	}
	
	/**
	 * 根据org获取对应的map对象
	 * @param org
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getMapByMenu(Menu menu) {
		Map map = new HashMap<>();
		map.put("id", menu.getId());
		map.put("supMenuId", menu.getSupMenuId());
		map.put("text", menu.getText());
		map.put("menuUrl", menu.getMenuUrl());
		map.put("menuVisible", menu.isMenuVisible());
		map.put("menuEnable", menu.isMenuEnable());
		map.put("iconClass", menu.getIconClass());
		map.put("serial",menu.getSerial());
		map.put("creator", menu.getCreator());
		map.put("createTime", menu.getCreateTime());
		map.put("updator", menu.getUpdator());
		map.put("updateTime", menu.getUpdateTime());
		return map;
	}

	@Override
	public String getAllMenuByPagination(Pagination pagination) throws JsonProcessingException {
		// TODO Auto-generated method stub
		List<Menu> listMenu = this.findByPaging(pagination.getPage(), pagination.getRows());
		
		return getJsonMenuObj(listMenu);
	}

	@Override
	public String getAllMenus() throws JsonProcessingException {
		// TODO Auto-generated method stub
		return getJsonMenuObj(this.findAll());
	}
	@Override
	public String getAllMenusIdText() throws JsonProcessingException {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String addMenu(Menu menu) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			menu.setCreator("zgy");
			this.Add(menu);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("新增菜单失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}

	@Override
	public String updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Menu updatedMenu = this.findById(menu.getId());
			updatedMenu.setText(menu.getText());
			updatedMenu.setMenuUrl(menu.getMenuUrl());
			updatedMenu.setSerial(menu.getSerial());
			updatedMenu.setUpdator("zgy");
			updatedMenu.setIconClass(menu.getIconClass());
			this.Update(menu);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("更新菜单失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}

	@Override
	public String deleteMenu(Long Id) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			this.DeleteByObject(this.findById(Id));
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("删除菜单失败");
			resultCode.setDetailMsg(e.getMessage());
			e.printStackTrace();
		}
		return ResultCode.getJsonString(resultCode);
	}

	@Override
	public String updateBtnListForMenu(Long Id, String commonIdList) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Menu menu = menuDao.getMenuForBtns(Id);
			menu.clearBtn();
			
			if (commonIdList!="") {
				List<Long> listBtnIds = Arrays.asList(commonIdList.split(","))
									.stream().map(String::trim)
									.map(Long::parseLong).collect(Collectors.toList());
				listBtnIds.stream().forEach(btnId->{
					menu.addBtn(btnDao.getBtnForMenu(btnId));
				});
			}
			this.Update(menu);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("关联按钮失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		
		return ResultCode.getJsonString(resultCode);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
@Override
	public String getBtnsByMenuId(Long Id) {
		// TODO Auto-generated method stub
		List<Button> listBtns = menuDao.getMenuForBtns(Id).getButtons().stream().collect(Collectors.toList());
		List<Map> listMap = new ArrayList<>();
		listBtns.stream().forEach(btn->{
			Map map = new HashMap<>();
			map.put("Id", btn.getId());
			listMap.add(map);
		});
		ObjectMapper wrapper = new ObjectMapper();
		try {
			return wrapper.writeValueAsString(listMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	
}
