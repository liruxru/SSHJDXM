package com.oracle.action.admin.qiantai;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.service.RoomService;
import com.oracle.service.RoomTypeService;

/**
 * @author oracle
 *
 */
@Namespace("/room")
@ParentPackage("struts-default")
@Controller  
@Scope("proptype")  
public class RoomAction extends ActionSupport {
	
	@Autowired
	private RoomService  roomService;
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	@Autowired
	private RoomTypeService roomTypeService;
	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}


	
	/**
	 * 查看所有房间，房间状态，预定情况
	 * @return
	 */

	@Action(value="findAll",results={
			@Result(name=SUCCESS,location="/page/user/room/list.jsp")
	})
	public String findAll(){
		ActionContext.getContext().put("roomtypeList", roomTypeService.findAll());
		ActionContext.getContext().put("roomList", roomService.findAll(1));
		return SUCCESS;
	}
	
	
	
}
