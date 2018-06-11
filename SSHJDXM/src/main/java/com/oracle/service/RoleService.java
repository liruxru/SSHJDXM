package com.oracle.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.AdminrolesDAO;
import com.oracle.dao.PermissionDAO;
import com.oracle.entity.Adminroles;
import com.oracle.entity.Permission;

@Component
public class RoleService {

	@Autowired
	private AdminrolesDAO adminrolesDAO;
	
	@Autowired
	private PermissionDAO permissionDAO;
	
	
	public PermissionDAO getPermissionDAO() {
		return permissionDAO;
	}
	public void setPermissionDAO(PermissionDAO permissionDAO) {
		this.permissionDAO = permissionDAO;
	}
	public AdminrolesDAO getAdminrolesDAO() {
		return adminrolesDAO;
	}
	public void setAdminrolesDAO(AdminrolesDAO adminrolesDAO) {
		this.adminrolesDAO = adminrolesDAO;
	}

	public List<Adminroles> roleList() {
		List<Adminroles> adminroles=adminrolesDAO.findAll();
		return adminroles;
	}
	//模糊查询不同的权限
	public List<Permission> findPermission(String urlString) {
		List<Permission> listper=adminrolesDAO.findPermissionByurl(urlString);
		return listper;
	}
	//修改或添加角色
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void add(Adminroles adminrole, Integer[] perIds) {
		Set<Permission> setPre=new HashSet<Permission>();
		//修改操作
		if (adminrole.getId()!=null) {
			Adminroles adminroleDo=adminrolesDAO.findById(adminrole.getId()); //转化为持久层对象
			adminroleDo.setModitfydate(new Timestamp(new Date().getTime()));
			adminroleDo.setCreatedate(adminrole.getCreatedate());
			Set<Permission> setperDo=adminroleDo.getPermissions();//获得数据库中存储的权限
			Set<Permission>  setPer=new HashSet<Permission>();
			Set<Permission>  s=new HashSet<Permission>();
			for (Permission permission : setperDo) {  //转化为持久化对象
				setPer.add(permissionDAO.findById(permission.getId()));
			}
			for (Permission per : setPer) {
				per.getAdminroleses().remove(adminroleDo);  
			}
			for (Integer p : perIds) {
				Permission pp=permissionDAO.findById(p);
				pp.getAdminroleses().add(adminroleDo);
				s.add(pp);
			}
			adminroleDo.setPermissions(s);
		}else {
			
			for (Integer per : perIds) {
				Permission p=permissionDAO.findById(per);
				p.getAdminroleses().add(adminrole);
				setPre.add(p);
			}
			adminrole.setPermissions(setPre);
			adminrole.setCreatedate(new Timestamp(new Date().getTime()));
			adminrolesDAO.save(adminrole);
		}
		
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public Adminroles findRolesById(Integer id) {
		return adminrolesDAO.findById(id);
	}
	
	//删除角色
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Integer id) {
		Adminroles adminroleDo=adminrolesDAO.findById(id); //转化为持久层对象
		Set<Permission> setperDo=adminroleDo.getPermissions();//获得数据库中存储的权限
		
		for (Permission per : setperDo) {
			adminrolesDAO.deleteAdminroleAndPer(adminroleDo.getId(),per.getId());
		}
		adminrolesDAO.delete(adminroleDo);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean checkRoleName(String roleName) {
		if (adminrolesDAO.findByRolename(roleName)==null) {
			return false;
		}else {
			return true;
		}
		
	}
	

}
