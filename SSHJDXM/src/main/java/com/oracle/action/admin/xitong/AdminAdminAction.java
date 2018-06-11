package com.oracle.action.admin.xitong;

import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.entity.Adminroles;
import com.oracle.entity.Admins;
import com.oracle.entity.Permission;
import com.oracle.service.AdminService;
import com.oracle.service.StaffsService;

@Controller
@Namespace("/admin/admin")
@ParentPackage("json-default")
public class AdminAdminAction {
	
	@Autowired
	private AdminService adminService;
	private Admins admin;
	private Integer[] roleIds;
	private JSONObject JestText;
	private String loginName;
	

	public JSONObject getJestText() {
		return JestText;
	}
	public void setJestText(JSONObject jestText) {
		JestText = jestText;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	public Admins getAdmin() {
		return admin;
	}
	public void setAdmin(Admins admin) {
		this.admin = admin;
	}
	
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	@Resource(name="StaffsService")
	private StaffsService staffsService;
	public StaffsService getStaffsService() {
		return staffsService;
	}
	public void setStaffsService(StaffsService staffsService) {
		this.staffsService = staffsService;
	}
	
	//校验管理员
	@Action(value="checkadmin",results={
			@Result(name="success",type = "json",params={"root","0"}),
			@Result(name="error",type = "json",params={"root","1"})
	})
	public String checkRoleName(){
		if (adminService.checkRoleName(loginName)) {
			return "success";
		}else {
			return "error";
		}
	}
	
	
	
	//浏览管理员
	@Action(value="adminlist",results={
			@Result(name="success",location="/page/admin/xitong/adminList.jsp")
	})
	public String adminList(){
		ActionContext.getContext().put("adminlist",adminService.adminList());
		return "success";
	}
	
	//添加管理员
	@Action(value="adminadd",results={
			@Result(name="success",type="redirectAction",location="adminlist")
	})
	public String adminAdd(){
		adminService.addAdmin(admin,roleIds);
		return "success";
		
	}
	
	//编辑管理员
	@Action(value="adminedit",results={
			@Result(name="success",location="/page/admin/xitong/adminAdd.jsp")
	})
	public String adminEdit(){
		if (admin!=null) {
			Admins adminDo=adminService.getAdminsById(admin.getId());
			Set<Adminroles> setRole=adminDo.getAdminroleses();
			System.out.println(adminDo.getStaffs().getId());
			ActionContext.getContext().put("setRole", setRole);
			ActionContext.getContext().put("adminDo", adminDo);
		}
		ActionContext.getContext().put("adminrole", adminService.listAdminrole());
		ActionContext.getContext().put("staffsAll", staffsService.findAll());
		return "success";
	}
	
	//删除角色
	@Action(value="admindelete",results={
			@Result(name="success",type="json",params={"root","0"})
	})
	public String adminDelete(){
		adminService.delete(admin.getId());
		return "success";
	}
	
}
