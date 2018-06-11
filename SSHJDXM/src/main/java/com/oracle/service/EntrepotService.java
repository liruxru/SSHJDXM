package com.oracle.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.EntrepotDAO;
import com.oracle.entity.Entrepot;

@Service("entrepotService")
public class EntrepotService {
	@Resource(name="EntrepotDAO")
	private EntrepotDAO entrepotDAO;

	public EntrepotDAO getEntrepotDAO() {
		return entrepotDAO;
	}

	public void setEntrepotDAO(EntrepotDAO entrepotDAO) {
		this.entrepotDAO = entrepotDAO;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Entrepot> list(){
		List<Entrepot> list=entrepotDAO.getAll();
		return list;
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean save(Entrepot entrepot) {
		entrepotDAO.attachDirty(entrepot);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean delete(Entrepot entrepot) {
		entrepotDAO.delete(entrepot);
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public Entrepot findById(Integer id) {
		Entrepot entrepot = entrepotDAO.findById(id);
		return entrepot;
	}	

}
