package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.UsersvipDAO;
import com.oracle.entity.Usersvip;

@Service
public class UserVipService {

	@Autowired
	private UsersvipDAO usersvipDAO;

	public UsersvipDAO getUsersvipDAO() {
		return usersvipDAO;
	}
	public void setUsersvipDAO(UsersvipDAO usersvipDAO) {
		this.usersvipDAO = usersvipDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Usersvip> findAll(){
		return usersvipDAO.findAll();
	}
	
}
