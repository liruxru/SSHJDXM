package com.oracle.action;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.entity.Admins;
import com.oracle.service.AdminLoginService;
import com.oracle.service.RoomService;
import com.oracle.service.RoomUserService;


@ParentPackage("json-default")
@Namespace("/")
@Controller
@Scope("proptype")
public class AdminLoginAction {
	private Admins admins;
	private AdminLoginService adminLoginService;
	private RoomService roomService;
	private JSONObject JestText;
	private RoomUserService roomUserService;
	
	public RoomUserService getRoomUserService() {
		return roomUserService;
	}
	public void setRoomUserService(RoomUserService roomUserService) {
		this.roomUserService = roomUserService;
	}
	public JSONObject getJestText() {
		return JestText;
	}
	public void setJestText(JSONObject jestText) {
		JestText = jestText;
	}
	public Admins getAdmins() {
		return admins;
	}
	public void setAdmins(Admins admins) {
		this.admins = admins;
	}
	public RoomService getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	public AdminLoginService getAdminLoginService() {
		return adminLoginService;
	}
	public void setAdminLoginService(AdminLoginService adminLoginService) {
		this.adminLoginService = adminLoginService;
	}

	//adminlogin
	@Action(value="adminLogin",results={
			@Result(name="success",type = "json",params={"root","0"}),
			@Result(name="error",type = "json",params={"root","1"})
	})
	public String adminLogin(){
		if(adminLoginService.adminsLogin(admins)){
			ActionContext.getContext().getSession().put("admin", admins);
			return "success";
		}else{
			ActionContext.getContext().put("admin", admins);
			return "error";
		}
	}
}
