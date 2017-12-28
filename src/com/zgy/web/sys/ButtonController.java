package com.zgy.web.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zgy.data.service.Interface.sys.IButtonService;
import com.zgy.model.Button;
import com.zgy.model.Pagination;
import com.zgy.util.ResultCode;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:21:51
*/
@Controller
@RequestMapping(value="btns",produces = "text/html;charset=UTF-8")
public class ButtonController {
	@Resource(name="btnService")
	IButtonService btnService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showBtnPages() {
		return "users/btn";
	}
	/**
	 * 根据分页获取按钮列表
	 * @param pagination
	 * @return
	 */
	@RequestMapping(value="getAllBtnsByPaging")
	@ResponseBody
	public String getAllBtnsByPagination(Pagination pagination) {
		return btnService.getAllBtnsByPagination(pagination);
	}
	/**
	 * 获取所有的按钮
	 * @return
	 */
	@RequestMapping(value="getAllBtns")
	@ResponseBody
	public String getAllBtns() {
		return btnService.getAllBtns();
	}
	/**
	 * 新增按钮
	 * @param button
	 * @return
	 */
	@RequestMapping(value="addBtn")
	@ResponseBody
	public String addBtn(Button button) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			button.setCreator("ZGY");
			btnService.Add(button);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("删除按钮失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}
	/**
	 * 更新按钮
	 * @param button
	 * @return
	 */
	@RequestMapping(value="updateBtn")
	@ResponseBody
	public String updateBtn(Button button) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			Button updatedBtn = btnService.findById(button.getId());
			updatedBtn.setBtnName(button.getBtnName());
			updatedBtn.setBtnEvent(button.getBtnEvent());
			updatedBtn.setBtnVisible(button.isBtnVisible());
			updatedBtn.setBtnEnable(button.isBtnEnable());
			updatedBtn.setSerial(button.getSerial());
			updatedBtn.setUpdator("ButtonController--updteBtn");
			btnService.Update(updatedBtn);
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("删除按钮失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}
	/**
	 * 删除按钮
	 * @param id
	 * @return
	 */
	@RequestMapping(value="deleteBtn")
	@ResponseBody
	public String deleteBtn(Long Id) {
		ResultCode resultCode = ResultCode.getSuccessResult();
		try {
			btnService.DeleteByObject(btnService.findById(Id));
		} catch (Exception e) {
			// TODO: handle exception
			resultCode.setCode(false);
			resultCode.setMsg("删除按钮失败");
			resultCode.setDetailMsg(e.getMessage());
		}
		return ResultCode.getJsonString(resultCode);
	}
	@RequestMapping(value="updateMenuListForBtn")
	@ResponseBody
	public String updateMenuListForBtn(Long Id,String commonIdList) {
		return btnService.updateMenuListForBtn(Id, commonIdList);
	}
	@RequestMapping(value="getMenusByBtnId")
	@ResponseBody
	public String getMenusByBtnId(Long Id){
		return btnService.getMenusByBtnId(Id);
	}
}
