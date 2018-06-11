package com.oracle.action.admin.xitong;

import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.entity.Adminroles;
import com.oracle.entity.Permission;
import com.oracle.service.RoleService;

@ParentPackage("json-default")
@Namespace("/admin/adminrole")
@Controller
public class AdminRoleAction {
	
	@Autowired
	private RoleService roleService;
	private Adminroles adminrole;
	private Integer[] perIds;
	private JSONObject JestText;
	private String roleName;
	
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public JSONObject getJestText() {
		return JestText;
	}
	public void setJestText(JSONObject jestText) {
		JestText = jestText;
	}
	public Integer[] getPerIds() {
		return perIds;
	}
	public void setPerIds(Integer[] perIds) {
		this.perIds = perIds;
	}
	public Adminroles getAdminrole() {
		return adminrole;
	}
	public void setAdminrole(Adminroles adminrole) {
		this.adminrole = adminrole;
	}
	public RoleService getRoleService() {
		return roleService;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Action(value="checkrolename",results={
			@Result(name="success",type = "json",params={"root","0"}),
			@Result(name="error",type = "json",params={"root","1"})
	})
	public String checkRoleName(){
		if (roleService.checkRoleName(roleName)) {
			return "success";
		}else {
			return "error";
		}
	}
	
	//浏览全部角色
	@Action(value="adminroleslist",results={
		@Result(name="success",location="/page/admin/xitong/roleList.jsp")	
	})
	public String rolesList(){
		ActionContext.getContext().put("rolelist", roleService.roleList());
		return "success";
	}
	//编辑或修改角色
	@Action(value="adminrolesedit",results={
			@Result(name="success",location="/page/admin/xitong/roleAdd.jsp")
	})
	public String rolesEdit(){
		if (adminrole!=null) {
			Adminroles adminroleDo=roleService.findRolesById(adminrole.getId());
			Set<Permission> setPer=adminroleDo.getPermissions();
			ActionContext.getContext().put("setPer", setPer);
			ActionContext.getContext().put("adminroleDo", adminroleDo);
			
		}
		
		//系统管理员角色所有权限
		ActionContext.getContext().put("adminPerAll", roleService.findPermission("/admin/admin/"));
		ActionContext.getContext().put("adminrolePerAll", roleService.findPermission("/admin/adminrole/"));
		//前台管理员角色
		ActionContext.getContext().put("qiantaiPerAll", roleService.findPermission("/admin/qiantai/"));
		//会员管理
		ActionContext.getContext().put("huiyuanPerAll", roleService.findPermission("/admin/huiyuan/"));
		//员工管理
		ActionContext.getContext().put("staffsPerAll", roleService.findPermission("/admin/staffs/"));
		//仓库管理
		ActionContext.getContext().put("entrepotPerAll", roleService.findPermission("/admin/entrepot/"));
		ActionContext.getContext().put("caigouPerAll", roleService.findPermission("/admin/caigou/"));
		ActionContext.getContext().put("kucunPerAll", roleService.findPermission("/admin/kucun/"));
		ActionContext.getContext().put("repairsPerAll", roleService.findPermission("/admin/repair/"));
		ActionContext.getContext().put("providerPerAll", roleService.findPermission("/admin/provider/"));
		//财务管理
		ActionContext.getContext().put("assetsPerAll", roleService.findPermission("/admin/assets/"));
		return "success";
	}
	
	@Action(value="adminrolesadd",results={
			@Result(name="success",type="redirectAction",location="adminroleslist")
			
	})
	public String rolesadd(){
		roleService.add(adminrole,perIds);
		return "success";
		
	}
	@Action(value="adminrolesdelete",results={
			@Result(name="success",type="json",params={"root","0"})
	})
	public String roleDelete(){
		roleService.delete(adminrole.getId());
		return "success";
	}
}
