package com.zgy.web.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.hibernate.annotations.ResultCheckStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.config.AopConfig;
import com.zgy.config.LogExecutionTime;
import com.zgy.data.service.Interface.sys.IRoleService;
import com.zgy.data.service.Interface.sys.IUserService;
import com.zgy.model.Role;
import com.zgy.model.User;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月30日 下午9:21:03
*/
@Controller
@RequestMapping(value="/roles")
public class RoleController {
	private final static Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Resource(name = "roleService")
	private IRoleService roleService;
	@Resource(name="userService")
	private IUserService userService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showRoles() {		
		return "users/roles";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/allRoles",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String allRoles(@RequestParam int page,@RequestParam int rows) throws JsonProcessingException {
		logger.info("加载数据列表");
		System.out.println("Roles");
		
		List<Role> allRoles = roleService.findByPaging(page, rows);
		
		ObjectMapper wrapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<>();
		map.put("total", roleService.getTotalCount());
		map.put("rows", allRoles);
		String result = wrapper.writeValueAsString(map);
		//log.info(result);
		return result;
	}
	/**
	 * 获取所有的角色信息
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="getAllRoles")
	@ResponseBody
	@LogExecutionTime
	private String getAllRoles() throws JsonProcessingException {
		logger.info("加载数据列表");
		List<Role> allRoles = roleService.findAll();
		ObjectMapper wrapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<>();
		String result = wrapper.writeValueAsString(allRoles); 
		return result;
	}
	
	@RequestMapping(value="/addRole",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addRole(Role role) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			role.setCreator("zgy");
		    roleService.Add(role);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("添加用户失败");
			resultCode.setDetailMsg(e.getMessage());
		}		
		return ResultCode.getJsonString(resultCode);
	}
	@RequestMapping(value="updateRole",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateRole(Role role) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Role updatedRole = roleService.findById(role.getId());
			updatedRole.setUpdator("zgyd");
			updatedRole.setRoleName(role.getRoleName());
			updatedRole.setSerial(role.getSerial());
			roleService.Update(role);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("更新角色失败");
			resultCode.setDetailMsg(e.getMessage());
		}		
		return ResultCode.getJsonString(resultCode);
	}
	
	@RequestMapping(value="deleteRole",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteRole(Role role) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Role deleteRole = roleService.findById(role.getId());
			roleService.DeleteByObject(deleteRole);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("删除角色失败");
			resultCode.setDetailMsg(e.getMessage());
		}				
		return ResultCode.getJsonString(resultCode);
	}
	@RequestMapping(value="/updateCross")
	@ResponseBody
	public void updateRoleCross() {
		Role role = roleService.findById(10L);
		User user1 = userService.findById(115L);
		User user2 = userService.findById(113L);
		role.addUser(user1);
		role.addUser(user2);
		roleService.Update(role);
	}
	/**
	 * 根据角色ID获取用户列表
	 * @param roleId 角色id
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="getUsersByRoleId")
	@ResponseBody
	public String getUsersByRoleId(Long roleId) throws JsonProcessingException{
		return roleService.getUsersByRoleId(roleId);
	}
	/**
	 * 更新角色与用户的关联，一个角色可以对应多个用户
	 * @param Id 角色ID
	 * @param commonIdList 用户ID列表
	 * @return
	 */
	@RequestMapping(value="updateUserListForRole",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUserListForRole(Long Id,String commonIdList) {
		return roleService.updateUserListForRole(Id, commonIdList);
	}
}
