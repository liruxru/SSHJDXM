package com.oracle.service;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.AdminsDAO;
import com.oracle.entity.Admins;
@Component
public class AdminLoginService {
	@Autowired
	private AdminsDAO adminsDAO;
	
	
	public AdminsDAO getAdminsDAO() {
		return adminsDAO;
	}
	public void setAdminsDAO(AdminsDAO adminsDAO) {
		this.adminsDAO = adminsDAO;
	}

	
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Admins getAdminByLoginname(String loginname){
		Admins adminDo=(Admins) adminsDAO.findadminByLoginname(loginname);
		return adminDo;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean adminsLogin(Admins admins){
		String adminPassword=DigestUtils.md5Hex(admins.getPassword());//加密方法
		admins.setPassword(adminPassword);
		Admins adminDo=(Admins) adminsDAO.findadminByLoginname(admins.getLoginname());
		if (adminDo!=null) {
			if (adminDo.getPassword().equals(admins.getPassword())) {
				admins.setAdminroleses(adminDo.getAdminroleses());
				admins.setStaffs(adminDo.getStaffs());
				admins.setLogindate(new Timestamp(new Date().getTime()));
				admins.setId(adminDo.getId());
				adminDo.setLogindate(admins.getLogindate());
//				adminsDAO.attachDirty(adminDo);
				return true;
			}
		}
		return false;
	}
}
