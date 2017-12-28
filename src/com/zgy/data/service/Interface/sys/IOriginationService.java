package com.zgy.data.service.Interface.sys;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zgy.data.service.Interface.ICommonService;
import com.zgy.model.Origination;
import com.zgy.model.Pagination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:35:44
*/
public interface IOriginationService extends ICommonService<Origination, Long> {
	public String getAllOrgsByPagination(Pagination pagination) throws JsonProcessingException;
	public String getAllOrgs() throws JsonProcessingException;
	public String addOrg(Origination origination);
	public String updateOrg(Origination origination);
	public String deleteOrg(Long Id);
	public String getUsersByOrgId(Long Id);
	public String updateUserListForOrg(Long Id,String commonIdList);
}
