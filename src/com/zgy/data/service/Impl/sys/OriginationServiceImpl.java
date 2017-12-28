package com.zgy.data.service.Impl.sys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.hibernate.boot.jaxb.Origin;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.data.dao.Interface.IGenericDao;
import com.zgy.data.dao.Interface.sys.IOriginationDao;
import com.zgy.data.dao.Interface.sys.IUserDao;
import com.zgy.data.service.Impl.ServiceImpl;
import com.zgy.data.service.Interface.sys.IOriginationService;
import com.zgy.model.Origination;
import com.zgy.model.Pagination;
import com.zgy.model.Role;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:37:25
*/
@Service("orgService")
public class OriginationServiceImpl extends ServiceImpl<Origination, Long> implements IOriginationService {
	@Resource(name="orgDao")
	IOriginationDao orgDao;
	@Resource(name="userDao")
	IUserDao userDao;
	@Resource(name="orgDao")
	public void setDao(IGenericDao<Origination, Serializable> genericDao) {
		super.setDao(genericDao);
	}

	@Override
	public String getAllOrgsByPagination(Pagination pagination) throws JsonProcessingException {
		// TODO Auto-generated method stub
		List<Origination> listOrgs = this.findByPaging(pagination.getPage(), pagination.getRows());
		return getJsonOrgObj(listOrgs);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private String getJsonOrgObj(List<Origination> listOrgs) throws JsonProcessingException{
		List<Map> listMap = new ArrayList<>();
		ObjectMapper wrapper = new ObjectMapper();
		if (listOrgs!=null) {
			listOrgs.stream().forEach(org->{
				Map map = getMapByOrg(org);
				if (org.getSupOrgId()==null) {
					map.put("children", getChildMapByOrg(org));
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
	private List<Map> getChildMapByOrg(Origination org){
		List<Origination> listOrgs = this.getObjByNonId("supOrgId", String.valueOf(org.getId()));
		List<Map> listMaps = new ArrayList<>();
		if (listOrgs.size()>0) {
			listOrgs.stream().forEach(item->{
				Map map = getMapByOrg(item);
				map.put("children", getChildMapByOrg(item));
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
	private Map getMapByOrg(Origination org) {
		Map map = new HashMap<>();
		map.put("id", org.getId());
		map.put("supOrgID", org.getSupOrgId());
		map.put("text", org.getText());
		map.put("serial",org.getSerial());
		map.put("creator", org.getCreator());
		map.put("createTime", org.getCreateTime());
		map.put("updator", org.getUpdator());
		map.put("updateTime", org.getUpdateTime());
		return map;
	}

	@Override
	public String getAllOrgs() throws JsonProcessingException{
		// TODO Auto-generated method stub
		List<Origination> listOrgs = this.findAll();
		
		return getJsonOrgObj(listOrgs);
	}
	
	@Override
	public String addOrg(Origination origination) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			origination.setCreator("zgy");
			this.Add(origination);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("新增部门失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}

	@Override
	public String updateOrg(Origination origination) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Origination updatedOrg = this.findById(origination.getId());
			updatedOrg.setText(origination.getText());
			updatedOrg.setUpdator("zgy");
			updatedOrg.setSupOrgId(origination.getSupOrgId());
			updatedOrg.setSerial(origination.getSerial());
			this.Update(origination);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("新增部门失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}

	@Override
	public String deleteOrg(Long Id) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			this.DeleteByObject(this.findById(Id));
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("新增部门失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public String getUsersByOrgId(Long Id) {
		// TODO Auto-generated method stub
		Origination org = orgDao.getOrgForUserByOrgId(Id);
		List<Map> listMap = new ArrayList<>();
		org.getUsers().stream().forEach(user->{
			Map map = new HashMap<>();
			map.put("Id", user.getId());
			listMap.add(map);
		});
		ObjectMapper wrapper = new ObjectMapper();
		try {
			return wrapper.writeValueAsString(listMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

	@Override
	public String updateUserListForOrg(Long Id, String commonIdList) {
		// TODO Auto-generated method stub
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Origination org = orgDao.getOrgForUserByOrgId(Id);
			org.clearUser();
			
			if (commonIdList!="") {
				List<Long> listUserIds = Arrays.asList(commonIdList.split(","))
		                .stream()
		                .map(String::trim)
		                .map(Long::parseLong)
		                .collect(Collectors.toList());
				listUserIds.stream().forEach(userId->{
					org.addUser(userDao.getUserForOriginations(userId));
				});
			}		
			this.Update(org);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("关联用户失败");
			resultCode.setDetailMsg(e.getMessage());
		}		
		
		return ResultCode.getJsonString(resultCode);
	}
}
