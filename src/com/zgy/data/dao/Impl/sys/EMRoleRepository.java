package com.zgy.data.dao.Impl.sys;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.zgy.data.dao.Impl.EntityManagerDaoImpl;
import com.zgy.data.dao.Interface.sys.IRoleDao;
import com.zgy.model.Role;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月30日 下午8:21:09
*/
@Repository(value="roleDao")
@Primary
public class EMRoleRepository extends EntityManagerDaoImpl<Role, Long> implements IRoleDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Role getRoleById(Long roleId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.Role.colUsers");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		Role role = em.find(Role.class,roleId,hints);
		
		return role;
	}

}
