/*package com.oracle.action.admin.qiantai;


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomselect;
import com.oracle.entity.Users;
import com.oracle.entity.Usersputong;
import com.oracle.service.RoomSelectService;
import com.oracle.service.RoomService;

*//**
 * 如果预定，办理客户入住
 * @author oracle
 *
 *//*
@Controller
@Namespace("/admin/qiantai")
@ParentPackage("json-default")
public class RoomInAction extends ActionSupport{
	
	private Usersputong userFir;//接收普通用户参数	
	private Usersputong userTwo;
	private Date outDate;
	
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public void setUserFir(Usersputong userFir) {
		this.userFir = userFir;
	}
	
	public Usersputong getUserFir() {
		return userFir;
	}

	public Usersputong getUserTwo() {
		return userTwo;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public Integer getUseraId() {
		return useraId;
	}

	public Integer getUserbId() {
		return userbId;
	}

	public Integer getRoomId() {
		return roomId;
	}

	public RoomSelectService getRoomSelectService() {
		return roomSelectService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setUserTwo(Usersputong userTwo) {
		this.userTwo = userTwo;
	}

	private BigDecimal deposit;//接收押金	
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	//接收会员id
	private Integer useraId;
	public void setUseraId(Integer useraId) {
		this.useraId = useraId;
	}
	private Integer userbId;
	public void setUserbId(Integer userbId) {
		this.userbId = userbId;
	}
	
	//接收预定房间id
	private Integer roomId;
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	
	
	//接收房间价格
	private BigDecimal price;	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	private String phone;//接收电话号码参数	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	private Roomselect  roomSelect;	
	public Roomselect getRoomSelect() {
		return roomSelect;
	}
	public void setRoomSelect(Roomselect roomSelect) {
		this.roomSelect = roomSelect;
	}

	//处理异步请求
	private JSONObject jsonText;	
	public JSONObject getJsonText() {
		return jsonText;
	}
	public void setJsonText(JSONObject jsonText) {
		this.jsonText = jsonText;
	}
	
	

	@Autowired//处理预定
	private RoomSelectService roomSelectService; 
	public void setRoomSelectService(RoomSelectService roomSelectService) {
		this.roomSelectService = roomSelectService;
	}
	
	@Autowired//办理入住
	private RoomService roomService;	
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	*//**
	 * 通过电话号码查找用户预定,并且封装预定信息
	 * @return
	 *//*
	@Action(value="findReverseByPhone",results={
		@Result(name=SUCCESS,location="/page/admin/qiantai/reverse.jsp"),
		@Result(name=ERROR,location="/page/admin/qiantai/list.jsp")
	})
	public String findReverseByPhone(){
		
		try {
			ActionContext.getContext().put("userReverse", roomSelectService.findReverseByPhone(phone));
		} catch (Exception e) {
			ActionContext.getContext().put("phoneerror", e.getMessage());
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	*//**
	 * 办理用户入住
	 * 1.改变房间状态从预定到入住
	 * 2.创建roomUSers表，这个表存储了当天在住人的信息
	 * 3.通过user1查找用户普通，没有创建绑定 。 user2查找，没有创建
	 * 
	 *//*
	
	@Action(value="transRoomIn",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/admin/room","actionName","findAll"}),
			@Result(name=ERROR,location="/page/admin/qiantai/reverse.jsp"),
		})
	public String transRoomIn(){
		boolean bln=roomService.transUserInRoom(userFir,userTwo,useraId,roomId,deposit,roomSelect,outDate);
		if(bln){
			return SUCCESS;
		}
		ActionContext.getContext().put("errorUserIn", "系统出错,稍后再试或者联系管理员");

		return ERROR;		
		
	}
	*//**
	 *通过id获取room信息，给用户开房
	 *//*
	@Action(value="getRoomById",results={
			@Result(name=SUCCESS,location="/page/admin/qiantai/noReverse.jsp")
		})
	public String getRoomById(){
		Rooms room=roomService.findById(roomId);
		ActionContext.getContext().put("room", room);
		return SUCCESS;
		
	}
	
	*//**
	 * 通过身份证查询是否有用户是会员
	 * @return
	 *//*
	@Action(value="searchUser",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String searchUser(){
		Map<String,Users> map=new HashMap<String,Users>();		
		Users user1=roomService.findUser(userFir.getIdcard());
		Users user2=null;
		if(userTwo!=null&&!"".equals(userTwo.getIdcard())){
			user2=roomService.findUser(userTwo.getIdcard());
		}
		
		if(user1!=null){user1.getUsersvip().setUserses(null);}
		if(user2!=null){user2.getUsersvip().setUserses(null);}
		map.put("user1", user1);
		map.put("user2", user2);
		
		jsonText=JSONObject.fromObject(map);
		System.out.println(jsonText.toString());
		return SUCCESS;
		
	}
	
	*//**
	 * 通过电话号用户是会员
	 * @return
	 *//*
	@Action(value="searchUserByPhone",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String searchUserByPhone(){
		
		Users userVo=roomService.findUserByPhone(phone);	
				
		try {
			if(userVo!=null){userVo.getUsersvip().setUserses(null);}
			jsonText=JSONObject.fromObject(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
	*//**
	 * 为没有预定的客户办理入住
	 * 1.获取用户身份证,存储用户表
	 * 2.存储roomuser表
	 *//*
	@Action(value="transRoomInNoReverse",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/admin/room","actionName","findAll"}),
			@Result(name=ERROR,location="/page/admin/qiantai/noReverse.jsp")	
	})
	public String transRoomInNoReverse(){
		boolean bln=roomService.transUserInRoom(userFir,userTwo,useraId,userbId,roomId,deposit,price,outDate);
		if(bln){
			return SUCCESS;
		}
		
		
		ActionContext.getContext().put("errorUserIn", "系统出错,稍后再试或者联系管理员");
		return ERROR;		
		
				
	}
	
}
*/