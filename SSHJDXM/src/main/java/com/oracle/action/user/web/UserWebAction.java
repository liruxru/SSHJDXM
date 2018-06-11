package com.oracle.action.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.dao.UsersDAO;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomselect;
import com.oracle.entity.Users;
import com.oracle.service.RoomSelectService;
import com.oracle.service.RoomService;
import com.oracle.service.UserService;
import com.oracle.utils.UUIDUtil;

@Namespace("/user/yuding")
@ParentPackage("json-default")
@Controller  
@Scope("proptype") 
public class UserWebAction {
	@Autowired
	private RoomService roomService;
	private UserService userService;
	private RoomSelectService roomSelectService;
	private Date startDate;
	private Date endDate;
	private Integer position;
	private Integer roomId;
	private JSONObject josnText;
	
	
	
	
	
	public JSONObject getJosnText() {
		return josnText;
	}
	public void setJosnText(JSONObject josnText) {
		this.josnText = josnText;
	}
	public RoomSelectService getRoomSelectService() {
		return roomSelectService;
	}
	public void setRoomSelectService(RoomSelectService roomSelectService) {
		this.roomSelectService = roomSelectService;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	@Action(value="findAllByRoomStatues",results={
			@Result(name="success",location="/page/user/web/yuding.jsp")
	})
	public String findAllByRoomStatues(){
		ActionContext.getContext().put("startTime", "");
		ActionContext.getContext().put("endTime", "");
		ActionContext.getContext().put("listRoomsByType", roomService.findAllByType(2));
		return "success";
	}
	
	@Action(value="finRoomsByPosition",results={
			@Result(name="success",location="/page/user/web/lxhlist.jsp")
	})
	public String finRoomsByPosition(){
		ActionContext.getContext().put("roomListByPosition", roomService.listRoomsCanUse(startDate, endDate, position));
		ActionContext.getContext().getSession().put("position",position);
		ActionContext.getContext().getSession().put("startDate",startDate);
		ActionContext.getContext().getSession().put("endDate",endDate);
		return "success";
	}
	
	/**
	 * 
	 * @return
	 */
	@Action(value="finRoomsByPositionFromMain",results={
			@Result(name="success",location="/page/user/web/lxhlist.jsp")
	})
	public String finRoomsByPositionFromMain(){
		ActionContext.getContext().put("roomListByPosition", roomService.listRoomsCanUse(startDate, endDate, 1));
		ActionContext.getContext().getSession().put("position",position);
		ActionContext.getContext().getSession().put("startDate",startDate);
		ActionContext.getContext().getSession().put("endDate",endDate);
		return "success";
	}
	
	/**
	 * 完成验证用户是否登录功能
	 */
	@Action(value="checkuserloginINList",results={
			@Result(name="success",type="json",params={"root","josnText"}),
			@Result(name="error",type="json",params={"root","josnText"})
	})
	public String userlogin() throws IOException{
		Users user=(Users) ActionContext.getContext().getSession().get("webuser");
				
		if(user==null){
			Map<String, String> msg=new HashMap<String, String>();
			msg.put("msg", "没登录");
			josnText= JSONObject.fromObject(msg);
			return "error";
		}
		Map<String, String> msg=new HashMap<String, String>();
		msg.put("msg", "yes");
		josnText= JSONObject.fromObject(msg);
		return "success";
		
	}	
	
	
	/**
	 * 完成跳转到支付页面
	 * @return
	 * @throws IOException
	 */
	@Action(value="userOrderRoomsifLogin",results={
			@Result(name="success",location="/page/user/web/pay.jsp")
	})
	public String userOrderRooms() throws IOException{
		Users user=(Users) ActionContext.getContext().getSession().get("webuser");
		
		Roomselect roomselect = new Roomselect();
		Rooms room=roomService.findRoomSelectByRoomId(roomId);
		roomselect.setUsers(userService.findUserById(user.getId()));
		roomselect.setRooms(room);
		roomselect.setCreatedate(new Timestamp(startDate.getTime()));
		roomselect.setEnddate(new Timestamp(endDate.getTime()));
		roomselect.setPrice(BigDecimal.valueOf(room.getRoomtypes().getPrice()));
		
		ActionContext.getContext().put("roomprice", room.getRoomtypes().getPrice());
		ActionContext.getContext().put("uuidsn", UUIDUtil.randomUUID());
		
		ActionContext.getContext().getSession().put("roomSelect", roomselect);
		ActionContext.getContext().getSession().put("position", position);
		ActionContext.getContext().getSession().put("startDate", startDate);
		ActionContext.getContext().getSession().put("endDate", endDate);
		

		return "success";
		
	}
	/**
	 * 完成支付功能并返回列表页
	 */
	@Action(value="selectRoom",results={
			@Result(name="success",type="redirectAction",location="toByPosition")
	})
	public String selectRoom(){
		Roomselect roomselect=(Roomselect) ActionContext.getContext().getSession().get("roomSelect");
		roomSelectService.saveRoomSelect(roomselect);
		return "success";
	}
	/**
	 * 跳转byposition方法，解决加载问题
	 * @return
	 */
	@Action(value="toByPosition",results={
			@Result(name="success",location="/page/user/web/lxhlist.jsp")
	})
	public String toByPosition(){
		Date startDate= (Date) ActionContext.getContext().getSession().get("startDate");
		Date endDate= (Date) ActionContext.getContext().getSession().get("endDate");
		Integer position = (Integer) ActionContext.getContext().getSession().get("position");
		ActionContext.getContext().put("roomListByPosition", roomService.listRoomsCanUse(startDate, endDate, position));
		return "success";
	}

}
