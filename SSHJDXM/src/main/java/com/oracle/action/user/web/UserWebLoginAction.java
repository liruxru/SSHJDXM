package com.oracle.action.user.web;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.entity.Users;
import com.oracle.service.RoomSelectService;
import com.oracle.service.RoomService;
import com.oracle.service.TypeService;
import com.oracle.service.UserService;


@ParentPackage("json-default")
@Namespace("/user/web")
@Controller
public class UserWebLoginAction {
	private TypeService typeService;
	private UserService userService;
	private RoomService roomService;
	private String loginName;
	private String passWord;
	
	
	
	
	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public TypeService getTypeService() {
		return typeService;
	}

	public void setTypeService(TypeService typeService) {
		this.typeService = typeService;
	}
	
	
	// 跳转到home页面

	@Action(value = "login", results = { 
			@Result(name = "success", location = "/page/user/web/lxhlist.jsp"),
			@Result(name = "error", location = "/page/user/web/login.jsp")
		})
	public String home() throws Exception {
		Users user=new Users();
		user.setLoginname(loginName);
		user.setPassword(passWord);
		
		if(userService.login(user)){
			ActionContext.getContext().getSession().put("webuser", user);
			ActionContext.getContext().put("roomListByPosition", roomService.listRoomsCanUse(new Date(), new Date(), 1));
			ActionContext.getContext().put("position",1);
			
			Date date=new Date();//取时间
		    Calendar calendar = new GregorianCalendar();
		    calendar.setTime(date);
		    calendar.add(Calendar.DATE,1);
		    Date date2 = calendar.getTime();
			ActionContext.getContext().put("startDate",date);
			ActionContext.getContext().put("endDate",date2);
			return "success";
		}else{
			ActionContext.getContext().put("userLoginname", user.getLoginname());
			ActionContext.getContext().put("msg", "用户名或密码错误");
			return "error";
		}
		
	}
}
