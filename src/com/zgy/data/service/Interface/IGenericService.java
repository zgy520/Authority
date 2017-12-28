package com.zgy.data.service.Interface;

import java.io.Serializable;
import java.util.List;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年11月15日 上午7:11:13
*/
public interface IGenericService<T,PK extends Serializable> {
	public T Add(T t);  //添加
	public void Update(T t); //更新
	public void DeleteByObject(T t); //根据对象删除
	public T findById(PK id);  //根据id获取相应的对象
	public List<T> findAll();  //获取所有的数据
	public List<T> findByPaging(int pageNumber,int pageSize);
	public long getTotalCount();
	public List<T> getObjByNonId(String columnName,String columnValue);  //根据非id字段获取对象
	public void test();
}
