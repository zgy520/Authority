package com.zgy.data.dao.Impl.sys;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityGraph;

import org.springframework.stereotype.Repository;

import com.zgy.data.dao.Impl.EntityManagerDaoImpl;
import com.zgy.data.dao.Interface.sys.IOriginationDao;
import com.zgy.model.Origination;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午9:32:35
*/
@Repository("orgDao")
public class OriginationDaoImpl extends EntityManagerDaoImpl<Origination, Long> implements IOriginationDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Origination getOrgForUserByOrgId(Long orgId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.Origination.users");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		Origination origination = em.find(Origination.class,orgId,hints);
		return origination;
	}
}
