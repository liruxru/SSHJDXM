package com.oracle.action.goods;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.entity.Rooms;
import com.oracle.service.UserService;

@Namespace("/user/goods")
@ParentPackage("struts-default")
@Controller
public class UserBuyGoodsAction extends ActionSupport {
	
	private Rooms room;
	public void setRoom(Rooms room) {
		this.room = room;
	}
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	/**
	 * 用户购买商品
	 * @return
	 */
	@Action(value="loginBuyGoods",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/goods","actionName","list"}),
			@Result(name=ERROR,location="/page/user/index.jsp")
	})
	public String loginBuyGoods(){
		//通过房间号码获取房间信息，存储到session
		Rooms rooms=userService.findRoomByNo(room.getNo());
		if(rooms.getRoomstatus().getId()==5||rooms.getRoomstatus().getId()==4){
			String ip=getIpAddress(ServletActionContext.getRequest());
			
			if(rooms!=null&&ip.equals(rooms.getIp())){
				ActionContext.getContext().getSession().put("room", rooms);
				return SUCCESS;
			}
		}
		
		ActionContext.getContext().put("error","房间号码输入不正确或者该房间尚未住人");
		return ERROR;
		
		
	}
	
	private String getIpAddress(HttpServletRequest request) {  
	        String ip = request.getHeader("x-forwarded-for");  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("WL-Proxy-Client-IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_CLIENT_IP");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
	        }  
	        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	            ip = request.getRemoteAddr();  
	        }  
	        return ip;  
	    }  
}
