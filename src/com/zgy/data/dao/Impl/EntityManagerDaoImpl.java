package com.zgy.data.dao.Impl;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zgy.config.LogExecutionTime;
import com.zgy.data.dao.Interface.IGenericDao;

@Repository
@Transactional
public abstract class EntityManagerDaoImpl<T,PK extends Serializable> implements IGenericDao<T,PK> {
	protected final static Logger logger = Logger.getLogger(EntityManagerDaoImpl.class);
	private Class<T> clazz;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EntityManagerDaoImpl() {
		// TODO Auto-generated constructor stub
		Type type = getClass().getGenericSuperclass();
		java.lang.reflect.ParameterizedType pt = (java.lang.reflect.ParameterizedType)type;
		clazz = (Class)pt.getActualTypeArguments()[0];
	}
	
	
	@PersistenceContext
	protected EntityManager em;
	
	
	public T insert(T t) {
		// TODO Auto-generated method stub
		em.persist(t);
		em.flush();
		return t;
	}

	
	public void update(T t) {
		// TODO Auto-generated method stub
		try {
			em.merge(t);
			
		} catch (Exception e) {
			logger.error("更新发生错误:"+e.getMessage());
			e.printStackTrace();
		}
		
	}	

	
	public void delete(T t) {
		// TODO Auto-generated method stub
		if (t!=null) {
			em.remove(em.contains(t)?t:em.merge(t));
		}		
	}

	
	public T queryById(PK id) {
		// TODO Auto-generated method stub
		return (T)em.find(clazz, id);
	}

	
	@SuppressWarnings("unchecked")
	public List<T> queryAll() {
		// TODO Auto-generated method stub
		List<T> tList = em.createQuery("Select t from "+clazz.getSimpleName()+" t").getResultList();
		return tList;
	}
	
	
	public List<T> queryByPageNumberAndSize(int pageNumber,int pageSize){
		
		return test(pageNumber, pageSize);
	}
	private List<T> test(int pageNumber,int pageSize) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		//CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		//countQuery.select(criteriaBuilder.count(countQuery.from(clazz)));
		Root<T> from = criteriaQuery.from(clazz);
		CriteriaQuery<T> select = criteriaQuery.select(from);
		TypedQuery<T> typedQuery = em.createQuery(select);
		typedQuery.setFirstResult((pageNumber -1) * pageSize);
		typedQuery.setMaxResults(pageSize);
		List<T> tList = typedQuery.getResultList();
		//Long count = em.createQuery(countQuery).getSingleResult();
		//System.err.println("获取到的数量为:"+count);
		return tList;
	}
	public long getTotalCount() {
		long total = 0;  //总的数量
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(clazz)));
		total = em.createQuery(countQuery).getSingleResult();
		return total;
	}
	public List<T> getObjByNonId(String columnName,String columnValue) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(clazz);
		Root<T> from = criteriaQuery.from(clazz);
		criteriaQuery.select(from);
		criteriaQuery.where(builder.equal(from.get(columnName), columnValue));
		TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
		List<T> listT = null;
		try {
			listT = typedQuery.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return listT;	
	}
	@Override
	public void test() {
		Metamodel metamodel = em.getMetamodel();
		Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();
		managedTypes.stream().forEach(type->{
			logger.info("persistenceType="+type.getPersistenceType());
			logger.info("itemType="+type.getSingularAttribute("loginName").getJavaType());
		});
	}
}
