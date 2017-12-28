package com.zgy.data.service.Interface.sys;

import com.zgy.data.service.Interface.ICommonService;
import com.zgy.model.Button;
import com.zgy.model.Pagination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:18:00
*/
public interface IButtonService extends ICommonService<Button, Long> {
	public String getAllBtns();
	public String getAllBtnsByPagination(Pagination pagination);
	public String updateMenuListForBtn(Long Id,String commonIdList);
	public String getMenusByBtnId(Long Id);
}
