/*package com.oracle.action.admin.qiantai;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.entity.Rooms;
import com.oracle.service.RoomService;
*//**
 * 办理用户退房
 * @author oracle
 *
 *//*
@Controller
@Namespace("/admin/qiantai")
@ParentPackage("json-default")
public class RoomOutAction extends ActionSupport{
	private Integer roomId;	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	@Autowired
	private RoomService roomService;
	public RoomService getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	@Action(value="userOut",results={
			@Result(name=SUCCESS,location="/page/admin/qiantai/userout.jsp")
	})
	public String userOut(){
		//通过房间id查询房间
		Rooms room=roomService.findById(roomId,true);
		ActionContext.getContext().getSession().remove("room");
		if(room!=null){
			System.out.println(room);
			ActionContext.getContext().put("room", room);
		}
				
		return SUCCESS;		
	}
	
	

	*//**
	 *刘洋:用户离店操作
	 *1.修改房间订单状态为4
	 *2.删除商品订单
	 *3.清空roomusers
	 *4.生成userOrders
	 * @return
	 *//*
	@Action(value="transRoomOut",results={
			@Result(name=SUCCESS,location="/page/admin/qiantai/userOutOk.jsp")
	})
	public String doTransRoomOut(){
		//通过房间id查询房间
		boolean bln=roomService.transRoomOut(roomId);
		if(bln)
		return SUCCESS;	
		return ERROR;
	}
}
*/