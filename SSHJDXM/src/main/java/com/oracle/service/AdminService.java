package com.oracle.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.AdminrolesDAO;
import com.oracle.dao.AdminsDAO;
import com.oracle.dao.StaffsDAO;
import com.oracle.entity.Adminroles;
import com.oracle.entity.Admins;
import com.oracle.entity.Permission;
import com.oracle.entity.Staffs;

@Component
public class AdminService {

	@Autowired
	private AdminsDAO adminsDAO;
	@Autowired
	private AdminrolesDAO adminrolesDAO;
	@Resource(name="StaffsDAO")
	private StaffsDAO staffsDAO;
	
	public AdminrolesDAO getAdminrolesDAO() {
		return adminrolesDAO;
	}

	public void setAdminrolesDAO(AdminrolesDAO adminrolesDAO) {
		this.adminrolesDAO = adminrolesDAO;
	}

	public AdminsDAO getAdminsDAO() {
		return adminsDAO;
	}

	public void setAdminsDAO(AdminsDAO adminsDAO) {
		this.adminsDAO = adminsDAO;
	}


	public List<Admins> adminList() {
		List<Admins> listAdmins=adminsDAO.findAll();
		return listAdmins;
	}

	public List<Adminroles> listAdminrole() {
		List<Adminroles> list=adminrolesDAO.findAll();
		return list;
	}

	//增加修改管理员
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void addAdmin(Admins admin, Integer[] roleIds) {
		Set<Adminroles> setAdminroles=new HashSet<Adminroles>();
		if (admin.getId()!=null) {
			Admins adminDo=adminsDAO.findById(admin.getId());
			for (Adminroles role : adminDo.getAdminroleses()) {
				System.out.println("管理员角色的主键"+role.getId());
				adminsDAO.deleteAdminRole(adminDo.getId(),role.getId());
			}
			for (Integer aid : roleIds) {
				Adminroles ar=adminrolesDAO.findById(aid);
				ar.getAdminses().add(adminDo);
				setAdminroles.add(ar);
			}
			adminDo.setLoginname(admin.getLoginname());
			adminDo.setPassword(admin.getPassword());
			adminDo.setStaffs(admin.getStaffs());
			adminDo.setAdminroleses(setAdminroles);
			adminDo.setModitfydate(new Timestamp(new Date().getTime()));
			
		}else {
			for (Integer aid : roleIds) {
				Adminroles ar=adminrolesDAO.findById(aid);
				ar.getAdminses().add(admin);
				setAdminroles.add(ar);
			}
			admin.setAdminroleses(setAdminroles);
			admin.setCreatedate(new Timestamp(new Date().getTime()));
			adminsDAO.save(admin);
		}
		
	}

	public Admins getAdminsById(Integer id) {
		
		return adminsDAO.findById(id);
	}

	//删除管理员
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Integer id) {
		Admins adminDo=adminsDAO.findById(id); //转化为持久层对象
		Set<Adminroles> setadminRoles=adminDo.getAdminroleses();//获得数据库中存储的角色
		for (Adminroles adminrole : setadminRoles) {
			adminsDAO.deleteAdminRole(adminDo.getId(), adminrole.getId());
		}
		adminsDAO.delete(adminDo);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean checkRoleName(String loginName) {
		if (adminsDAO.findadminByLoginname(loginName)==null) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
