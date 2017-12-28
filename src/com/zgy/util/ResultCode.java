package com.zgy.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午4:06:19
*/
public class ResultCode {
	private boolean code;
	private String msg;
	private String detailMsg;
	private static ResultCode resultCode;
	
	public ResultCode(boolean code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	/**
	 * 获取成功时的resultCode
	 * @return
	 */
	public static ResultCode getSuccessResult() {
		resultCode = new ResultCode(true, "成功");
		return resultCode;
	}
	/**
	 * 返回ResultCode所对应的json格式
	 * @param resultCode
	 * @return
	 */
	public static String getJsonString(ResultCode resultCode) {
		ObjectMapper wrapper = new ObjectMapper();
		String result = "";
		try {
			result = wrapper.writeValueAsString(resultCode);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage()+"msg="+resultCode.getMsg();
		}
		return result;
	}
	public boolean isCode() {
		return code;
	}
	public void setCode(boolean code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getDetailMsg() {
		return detailMsg;
	}
	public void setDetailMsg(String detailMsg) {
		this.detailMsg = detailMsg;
	}
}
