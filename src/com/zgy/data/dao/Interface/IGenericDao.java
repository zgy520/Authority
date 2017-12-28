package com.zgy.data.dao.Interface;

import java.io.Serializable;
import java.util.List;


public interface IGenericDao<T,PK extends Serializable> {
	public T insert(T t); //添加对象
	public void update(T t);  //更新对象
	public void delete(T t);  //删除对象
	public T queryById(PK id);  //根据主键查询对象
	public List<T> queryAll();  //获取所有的对象实体
	public List<T> queryByPageNumberAndSize(int pageNumber,int pageSize); //根据pageNumber和pageSize获取数据
	public long getTotalCount();  //数据库中具有的总数量
	public List<T> getObjByNonId(String columnName,String columnValue);  //根据非id字段获取对象
	public void test();
}
