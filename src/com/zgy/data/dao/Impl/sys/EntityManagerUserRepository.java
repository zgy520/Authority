package com.zgy.data.dao.Impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityGraph;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zgy.data.dao.Impl.EntityManagerDaoImpl;
import com.zgy.data.dao.Interface.sys.IUserDao;
import com.zgy.model.Role;
import com.zgy.model.User;

@Repository(value="userDao")
@Transactional
public class EntityManagerUserRepository extends EntityManagerDaoImpl<User,Long> implements IUserDao {

	public void testMethod() {
		System.out.println("你好");
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return queryAll();
	}

	@Override
	public void executeStoredProcedure(Long userId, List<Long> listRoleIds) {
		// TODO Auto-generated method stub
		StoredProcedureQuery storedProcedureQuery = em.createNamedStoredProcedureQuery("new_procedure");
		storedProcedureQuery.registerStoredProcedureParameter("userName", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("userCount", Long.class, ParameterMode.OUT);
		//storedProcedureQuery.registerStoredProcedureParameter("listRoleIds", List.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("userName", "ew3");
		storedProcedureQuery.execute();
		
		Long userCount = (Long)storedProcedureQuery.getOutputParameterValue("userCount");
		System.out.println("获取到的用户数量为:"+userCount);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void testd() {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery("select loginName as loginName,userName as userName from tuser",User.class);
		List<User> users = query.getResultList();
		for(User user : users) {
			System.out.println(user.getUserName());
		}
	}

	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void addUserBySP(User user) {
		// TODO Auto-generated method stub
		StoredProcedureQuery addUserNamedStoredProcedure = em.createNamedStoredProcedureQuery("addUser_sp");
		addUserNamedStoredProcedure.setParameter("loginName", user.getLoginName());
		addUserNamedStoredProcedure.setParameter("userName", user.getUserName());
		addUserNamedStoredProcedure.setParameter("serial", user.getSerial());
		
		StoredProcedureQuery selectQuery = em.createNamedStoredProcedureQuery("getCount_sp");
		selectQuery.setParameter("userName", user.getUserName());
		selectQuery.execute();
		
		StoredProcedureQuery userQuery = em.createNamedStoredProcedureQuery("getAllUsers");
		userQuery.execute();
		List<Object[]> allUsers = userQuery.getResultList();
		
		addUserNamedStoredProcedure.execute();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void testGraph() {
		// TODO Auto-generated method stub
		System.out.println("testGraph");
		EntityGraph graph = em.getEntityGraph("graph.User.setRoles");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		User user = em.find(User.class,137L,hints);
		user.getSetRoles();
		System.out.println(user.getSetRoles().size());
		User user1 = this.queryById(113L);
		user1.getSetRoles().stream().forEach(role->{
			System.out.println(role.getRoleName());
		});;
		System.out.println("end");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Role> getRolesByUserId(Long userId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.User.setRoles");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		User user = em.find(User.class,userId,hints);
		
		return user.getSetRoles().stream().collect(Collectors.toList());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.User.setRoles");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		User user = em.find(User.class,userId,hints);
		return user;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public User getUserForOriginations(Long userId) {
		// TODO Auto-generated method stub
		EntityGraph graph = em.getEntityGraph("graph.User.originations");
		
		Map hints = new HashMap();
		hints.put("javax.persistence.fetchgraph", graph);
		
		User user = em.find(User.class,userId,hints);
		return user;
	}
}
