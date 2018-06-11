package com.oracle.action.admin;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.service.RoomService;

@ParentPackage("json-default")
@Namespace("/admin")
@Controller
@Scope("proptype")
public class HomeAction {

	private RoomService roomService;
	
	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	/*//跳转倒登陆页面 
	@Action(value="goToAdminLogin",results={
			@Result(name="success",location="/page/adminLogin.jsp")
	})
	public String goToAdminLogin(){
		return "success";
	}*/
	//退出登陆  
	@Action(value="adminLoginOut",results={
			@Result(name="success",location="/page/adminLogin.jsp")
	})
	public String adminLoginOut(){
		ActionContext.getContext().getSession().remove("admin");
		return "success";
	}
	//跳转到home页面
	@Action(value="home",results={
			@Result(name="success",location="/page/admin/home.jsp")
	})
	public String home(){
		ActionContext.getContext().put("mapListRooms", roomService.findAlllv(1));
		ActionContext.getContext().getSession().remove("countRooms");
		ActionContext.getContext().getSession().put("countRooms", roomService.roomsCount());
		return "success";
	}
}
