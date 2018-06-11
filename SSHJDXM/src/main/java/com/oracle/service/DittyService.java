package com.oracle.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.DittyDAO;
import com.oracle.entity.Ditty;

@Service("dittyService")
public class DittyService {
	@Resource(name="DittyDAO")
	private DittyDAO dittyDAO;

	public DittyDAO getDittyDAO() {
		return dittyDAO;
	}
	public void setDittyDAO(DittyDAO dittyDAO) {
		this.dittyDAO = dittyDAO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Ditty> list(){
		List<Ditty> list = dittyDAO.getAll();
		return  list;
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(Ditty ditty) {
		if(ditty.getId()!=null){
			ditty.setModitfydate(new Timestamp(new Date().getTime()));
		}
		if(ditty.getCreatedate()==null){
			ditty.setCreatedate(new Timestamp(new Date().getTime()));
		}
		dittyDAO.attachDirty(ditty);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public Ditty findById(Integer id) {
		Ditty ditty = dittyDAO.findById(id);
		return ditty;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(Ditty ditty) {		
		dittyDAO.delete(ditty);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Ditty> getDittyByEntrepotId(Integer entrepotId){
		 List<Ditty> ls = dittyDAO.getDittyByEntrepotId(entrepotId);
		return ls;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Ditty> getDitty() {
		List<Ditty> list = dittyDAO.getDitty();
		return list;
	}
	

}
