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
import com.zgy.data.dao.Interface.sys.IButtonDao;
import com.zgy.data.dao.Interface.sys.IMenuDao;
import com.zgy.data.service.Impl.ServiceImpl;
import com.zgy.data.service.Interface.sys.IButtonService;
import com.zgy.model.Button;
import com.zgy.model.Menu;
import com.zgy.model.Pagination;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:18:31
*/
@Service("btnService")
public class ButtonServiceImpl extends ServiceImpl<Button, Long> implements IButtonService {
	
	@Resource(name="btnDao")
	private IButtonDao btnDao;
	@Resource(name="menuDao")
	private IMenuDao menuDao;
	
	@Resource(name="btnDao")
	public void setDao(IGenericDao<Button, Serializable> genericDao) {
		super.setDao(genericDao);
	}

	@Override
	public String getAllBtns() {
		// TODO Auto-generated method stub
		List<Button> listBtns = this.findAll();
		ObjectMapper wrapper = new ObjectMapper();
		String result;
		try {
			result = wrapper.writeValueAsString(listBtns);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	@Override
	public String getAllBtnsByPagination(Pagination pagination) {
		// TODO Auto-generated method stub
		List<Button> listBtns = this.findByPaging(pagination.getPage(), pagination.getRows());
		ObjectMapper wrapper = new ObjectMapper();
		String result;
		try {
			result = wrapper.writeValueAsString(listBtns);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}

	@Override
	public String updateMenuListForBtn(Long Id, String commonIdList) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Button btn = btnDao.getBtnForMenu(Id);
			btn.clearMenu();
			if (commonIdList!="") {
				List<Long> listMenuIds = Arrays.asList(commonIdList.split(","))
							.stream().map(String::trim)
							.map(Long::parseLong).collect(Collectors.toList());
				listMenuIds.stream().forEach(menuId->{
					btn.addMenu(menuDao.getMenuForBtns(menuId));
				});
			}
			this.Update(btn);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("按钮关联菜单失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}

	@Override
	public String getMenusByBtnId(Long Id) {
		// TODO Auto-generated method stub
		List<Menu> listMenus = btnDao.getBtnForMenu(Id).getMenus().stream().collect(Collectors.toList());
		List<Map> listMap = new ArrayList<>();
		listMenus.stream().forEach(menu->{
			Map map = new HashMap<>();
			map.put("Id", menu.getId());
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
