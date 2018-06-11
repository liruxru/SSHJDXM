package com.oracle.bean;

import java.util.List;
/**
 * 用于分页的工具
 * 
 * @author oracle
 *
 */
public class Page {
	private Integer count;   //总记录数. 在业务逻辑层中查询获得，
	private Integer totalPage;   //总页数。      通过计算
	private Integer currentPage; //当前页.   
	private List    list;        //用来装每次查看的数据集合
	
	
	//计算总页数的方法
	public static Integer getTotalPage(Integer count,Integer pageSize){
		return count%pageSize==0?count/pageSize:count/pageSize+1;
	}
	
	//计算当前数
	public static Integer getOffset(Integer currentPage,Integer pageSize){
		return pageSize*(currentPage-1);		
	}
	
	//计算每次取多少条数据
	public static Integer length(Integer pageSize){
		return pageSize;
		
	}
	

	public Page(Integer count, Integer totalPage, Integer currentPage, List list) {
		super();
		this.count = count;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.list = list;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
}
