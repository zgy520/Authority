package com.zgy.data.service.Impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zgy.data.dao.Interface.IGenericDao;
import com.zgy.data.service.Interface.IGenericService;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月15日 上午7:15:49
*/
@Service
public class ServiceImpl<T,PK extends Serializable> implements IGenericService<T, PK> {

	protected IGenericDao<T, Serializable> genericDao;
	@Resource(name="userDao")
	public void setDao(IGenericDao<T, Serializable> genericDao) {
		// TODO Auto-generated constructor stub
		this.genericDao = genericDao;
	}

	public T Add(T t) {
		// TODO Auto-generated method stub
		T obj = (T)genericDao.insert(t);
		return obj;
	}

	public void Update(T t) {
		// TODO Auto-generated method stub
		genericDao.update(t);
	}

	public void DeleteByObject(T t) {
		// TODO Auto-generated method stub
		genericDao.delete(t);
	}

	@SuppressWarnings("unchecked")
	public T findById(PK id) {
		// TODO Auto-generated method stub
		return (T)genericDao.queryById(id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return genericDao.queryAll();
	}

	public List<T> findByPaging(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return genericDao.queryByPageNumberAndSize(pageNumber, pageSize);
	}
	
	public long getTotalCount() {
		return genericDao.getTotalCount();
	}

	@Override
	public List<T> getObjByNonId(String columnName, String columnValue) {
		// TODO Auto-generated method stub
		return genericDao.getObjByNonId(columnName, columnValue);
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		genericDao.test();
	}
	

}
