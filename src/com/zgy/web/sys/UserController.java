package com.zgy.web.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zgy.data.service.Interface.sys.IRoleService;
import com.zgy.data.service.Interface.sys.IUserService;
import com.zgy.model.User;
import com.zgy.util.ResultCode;

@Controller
@RequestMapping(value="/users")
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	@Autowired @Qualifier("userService")
	private IUserService userServiceRepository;
	@Resource(name="roleService")
	private IRoleService roleService;
	//@Inject
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String showUsers(Model model) {
		return "users/user";
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/allUsers",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String allUsers(@RequestParam int page,@RequestParam int rows) throws JsonProcessingException {
		List<User> allUsers = userServiceRepository.findByPaging(page, rows);
		allUsers.get(0).getSetRoles();
		ObjectMapper wrapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<>();
		map.put("total", userServiceRepository.getTotalCount());
		map.put("rows", allUsers);
		String result = wrapper.writeValueAsString(map);
		//log.info(result);
		return result;
	}
	@RequestMapping(value="getAllUsers")
	@ResponseBody
	private String getAllRoles() throws JsonProcessingException {
		List<User> allUsers = userServiceRepository.findAll();
		ObjectMapper wrapper = new ObjectMapper();
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<>();
		String result = wrapper.writeValueAsString(allUsers); 
		return result;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/addUserT",method=RequestMethod.POST)
	@ResponseBody
	public String addUserT(HttpServletRequest request,HttpServletResponse response,User user) throws JsonProcessingException {
		//response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8020");
		@SuppressWarnings("rawtypes")
		Map map = new HashMap<>();
		ObjectMapper wrapper = new ObjectMapper();
		map.put("total", 20);
		map.put("34", 3);
		String result = wrapper.writeValueAsString(map);
		return result;
	}
	@RequestMapping(value="/addUser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String addUser(User user) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			user.setCreator("zgy");
			user.setLoginCount(1);
		    userServiceRepository.Add(user);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("新增用户失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		
		//userServiceRepository.addUserBySP(user);
		return ResultCode.getJsonString(resultCode);
	}
	@RequestMapping(value="updateUser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateUser(User user) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			logger.info("更新用户");
			User newuser = userServiceRepository.findById(user.getId());
			newuser.setLoginCount(newuser.getLoginCount() + 1);
			logger.info(user.getUserName());
			newuser.setLoginName(user.getLoginName());
			newuser.setUserName(user.getUserName());
			newuser.setEmail(user.getEmail());
			newuser.setSerial(user.getSerial());
			newuser.setUserPassword("$2a$10$5hEwpVQc3iS3M/pIIlniKOwvGCGPcmhZq5KeG04zSEf7sgmBrio.C");
			newuser.setCreator("zgy");
			newuser.setUpdator("zgyd");
			//Role role = new Role();
			//role.setRoleName("test");
			userServiceRepository.Update(newuser);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("更新用户失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		
		return ResultCode.getJsonString(resultCode);
	}
	@RequestMapping(value="deleteUser",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteUser(User user) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			List<User> theUser = userServiceRepository.getObjByNonId("loginName", user.getLoginName());
			if (theUser.size() > 0) {
				theUser.stream().forEach(user1->{
							
					User deleteUser = userServiceRepository.findById(user1.getId());
					userServiceRepository.DeleteByObject(deleteUser);
				});
			}
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("删除用户失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		
		return ResultCode.getJsonString(resultCode);
	}
	@RequestMapping(value="deleteU",produces="application/json;charset=utf-8")
	@ResponseBody
	private String deleteU(Long Id) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			User user = userServiceRepository.findById(Id);
			userServiceRepository.DeleteByObject(user);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("用户删除失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		
		return ResultCode.getJsonString(resultCode);
	}
	/**
	 * 根据用户Id获取所具有的角色
	 * @param userId
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="/getRoleByUserId")
	@ResponseBody
	public String getRoleByUserId(Long userId) throws JsonProcessingException {
		return userServiceRepository.getRoleByUserId(userId);
	}
	/**
	 * 为用户关联角色，一个用户可以同时具有多个角色
	 * @param Id
	 * @param commonIdList
	 * @return
	 */
	@RequestMapping(value="updateRoleListForUser")
	@ResponseBody
	public ResultCode updateRoleListForUser(Long Id,String commonIdList) {
		return userServiceRepository.updateRoleListForUser(Id, commonIdList);
	}
}
