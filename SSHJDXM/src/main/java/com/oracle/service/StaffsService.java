package com.oracle.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.bean.Page;
import com.oracle.dao.StaffsDAO;
import com.oracle.entity.Staffs;

@Service("StaffsService")
public class StaffsService {
	
	@Resource(name="StaffsDAO")
	private StaffsDAO staffsDAO;

	public StaffsDAO getStaffsDAO() {
		return staffsDAO;
	}
	public void setStaffsDAO(StaffsDAO staffsDAO) {
		this.staffsDAO = staffsDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Staffs> findAll(){
		List<Staffs> ls = staffsDAO.findAll();
		return ls;
		
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page list(Integer currentPage, Integer pageSize) {
		//计算总记录数
		Integer count =  staffsDAO.count();
		//计算总页数
		Integer totalPage = Page.getTotalPage(count, pageSize);
		//计算当前记录数
		Integer offset = Page.getOffset(currentPage, pageSize);
		//计算每次取得长度
		Integer length = Page.length(pageSize);
		//分页取到的集合
		List<Staffs> ls = staffsDAO.findAll(offset,length);
		Page page = new Page(count, totalPage, currentPage, ls);
		return page;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean save(Staffs staffs) {
		if(staffs.getId()!=null){
			staffs.setModitfydate(new Timestamp(new Date().getTime()));
		}
		if(staffs.getCreatedate()==null){
			staffs.setCreatedate(new Timestamp(new Date().getTime()));
		}
		staffsDAO.attachDirty(staffs);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Staffs findById(Integer id) {
		Staffs staffs = staffsDAO.findById(id);
		return staffs;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean delete(Staffs staffs) {
		staffsDAO.delete(staffs);
		return true;
	}
	

}
