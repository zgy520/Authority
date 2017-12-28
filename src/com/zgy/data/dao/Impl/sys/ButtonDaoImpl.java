package com.zgy.data.dao.Impl.sys;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.stereotype.Repository;

import com.zgy.data.dao.Impl.EntityManagerDaoImpl;
import com.zgy.data.dao.Interface.sys.IButtonDao;
import com.zgy.model.Button;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:16:09
*/
@Repository("btnDao")
public class ButtonDaoImpl extends EntityManagerDaoImpl<Button, Long> implements IButtonDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Button getBtnForMenu(Long btnId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.Button.menus");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		Button btn = em.find(Button.class,btnId,hints);
		return btn;
	}

}
