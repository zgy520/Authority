package com.zgy.web.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.data.service.Interface.sys.IOriginationService;
import com.zgy.model.Origination;
import com.zgy.model.Pagination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:41:36
*/
@Controller
@RequestMapping(value="orgs",produces = "text/html;charset=UTF-8")
public class OrgController {
	@Resource(name="orgService")
	IOriginationService orgService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showOrgPage() {
		return "users/org";
	}
	@RequestMapping(value="getAllOrgByPagination")
	@ResponseBody
	public String getAllOrgsByPagination(Pagination pagination) throws JsonProcessingException{
		return orgService.getAllOrgsByPagination(pagination);
	}
	@RequestMapping(value="getAllOrgs")
	@ResponseBody
	public String getAllOrgs() throws JsonProcessingException{
		return orgService.getAllOrgs();
	}
	@RequestMapping(value="addOrg")
	@ResponseBody
	public String addOrg(Origination origination) {
		return orgService.addOrg(origination);
	}
	@RequestMapping(value="updateOrg")
	@ResponseBody
	public String updateOrg(Origination origination) {
		return orgService.updateOrg(origination);
	}
	@RequestMapping(value="deleteOrg")
	@ResponseBody
	public String deleteOrg(Long Id) {
		return orgService.deleteOrg(Id);
	}
	@RequestMapping(value="getUsersByOrgId")
	@ResponseBody
	public String getUsersByOrgId(Long Id) {
		return orgService.getUsersByOrgId(Id);
	}
	@RequestMapping(value="updateUserListForOrg")
	@ResponseBody
	public String updateUserListForOrg(Long Id,String commonIdList) {
		return orgService.updateUserListForOrg(Id, commonIdList);
	}
}
