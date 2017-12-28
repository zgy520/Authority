package com.zgy.model;

import java.util.List;

/**
* @author 张广义 E-mail:a442391947@outlook.com
* @version 创建时间：2017年12月23日 下午7:24:51
*/
public class Pagination {
	private int page;
	private int rows;
	private int totalCount;
	private List listData;
	
	public Pagination(int page,int rows) {
		this.page = page;
		this.rows = rows;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List getListData() {
		return listData;
	}
	public void setListData(List listData) {
		this.listData = listData;
	}
	
	
}
