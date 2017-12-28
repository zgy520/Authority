package com.zgy.data.dao.Interface.sys;

import com.zgy.data.dao.Interface.ICommonDao;
import com.zgy.model.Button;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:15:23
*/
public interface IButtonDao extends ICommonDao<Button, Long> {
	public Button getBtnForMenu(Long btnId);  //根据按钮Id获取懒加载的按钮实体
}
