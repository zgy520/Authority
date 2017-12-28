package com.zgy.data.dao.Impl.sys;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.stereotype.Repository;

import com.zgy.data.dao.Impl.EntityManagerDaoImpl;
import com.zgy.data.dao.Interface.sys.IMenuDao;
import com.zgy.model.Menu;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:31:43
*/
@Repository("menuDao")
public class MenuDaoImpl extends EntityManagerDaoImpl<Menu, Long> implements IMenuDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Menu getMenuForBtns(Long menuId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.Menu.buttons");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		Menu menu = em.find(Menu.class,menuId,hints);
		return menu;
	}

}
