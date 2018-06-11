package com.oracle.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.RepairDAO;
import com.oracle.entity.Repair;

@Service("repairService")
public class RepairService {
	@Resource(name="RepairDAO")
	private RepairDAO repairDAO;

	public RepairDAO getRepairDAO() {
		return repairDAO;
	}
	public void setRepairDAO(RepairDAO repairDAO) {
		this.repairDAO = repairDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Repair> findAll(){
		return repairDAO.findAll();
		
	}
	



}
