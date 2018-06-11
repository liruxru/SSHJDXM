package com.oracle.action.user;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.bean.RoomUserMsg;
import com.oracle.comm.SH;
import com.oracle.entity.Customerorders;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomusers;
import com.oracle.entity.Users;
import com.oracle.service.RoomService;
import com.oracle.service.UserService;

@Namespace("/user")
@ParentPackage("json-default")
@Controller
public class UserAction extends ActionSupport {
	
	private Users user;
	private Rooms room;
	public void setRoom(Rooms room) {
		this.room = room;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Users getUser() {
		return user;
	}
	private JSONObject jsonText;

	public JSONObject getJsonText() {
		return jsonText;
	}
	public void setJsonText(JSONObject jsonText) {
		this.jsonText = jsonText;
	}
	@Autowired//自动注入
	private RoomService roomService;
	
	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 用户登录预定酒店申请
	 * @return
	 */
	@Action(value="loginReverse",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/room","actionName","findAll"}),
			@Result(name=ERROR,location="/page/user/index.jsp")
	})
	public String loginReverse(){
		
		try {
			System.out.println(user.getLoginname());
			System.out.println(user.getPassword());
			boolean bln=userService.login(user);
			if(bln){
				ActionContext.getContext().getSession().put(SH.SESSION_USER_KEY,user);
				return SUCCESS;
			}			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		ActionContext.getContext().put("error", "用户名或密码错误");
		return ERROR;
		
		
	}
	
	
	@Action(value="loginBuyGoods",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/goods","actionName","list"}),
			@Result(name=ERROR,location="/page/user/index.jsp")
	})
	public String loginBuyGoods(){
		
		try {
			boolean bln=userService.login(user);
			if(bln){
				ActionContext.getContext().getSession().put(SH.SESSION_USER_KEY,user);
				if(user.getUsersputong()==null||user.getUsersputong().getRoomuserses()==null){
					ActionContext.getContext().put("error", "不住店无法购物");
					return ERROR;
				}
				Set<Roomusers> rmuSet=user.getUsersputong().getRoomuserses();
				
				for (Roomusers roomusers : rmuSet) {
					
					if(room.getNo().equals(roomusers.getRooms().getNo())){
						return SUCCESS;
					}
				}
				ActionContext.getContext().put("error", "检查房间号是否正确");
				return ERROR;
				
			}			
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		ActionContext.getContext().put("error", "用户名或密码错误");
		return ERROR;
		
		
	}
	/**********************************************************************/
	@Action(value="roomUserMsg",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String roomUserMsg(){
		Rooms rooms=roomService.findById(room.getId(), true);
		RoomUserMsg roomUserMsg=null;
		if(rooms!=null){
			//获取房间当前入住信息
			Set<Roomusers> RoomusersSet=rooms.getRoomuserses();
			String[] roomspeoples=new String[2];int i=0;
			Timestamp roomszhudate=null;
			Timestamp roomslidate=null;
			for (Roomusers roomusers : RoomusersSet) {
				roomspeoples[i++]=roomusers.getUsersputong().getName();
				roomszhudate=roomusers.getCreatedate();
				roomslidate=roomusers.getModitfydate();			
			}
			BigDecimal roomsyouhuiprice=null;
			BigDecimal roomsgoodsprice=new BigDecimal(0);
			BigDecimal roomsyajin=rooms.getDeposit();
			Set<Customerorders>  customerordersSet=rooms.getCustomerorderses();
			for (Customerorders customerorders : customerordersSet) {
				if(customerorders.getUsersputong()==null){
					roomsgoodsprice=roomsgoodsprice.add(customerorders.getSum());
				}
				if(customerorders.getUsersputong()!=null){
					roomsyouhuiprice=customerorders.getSum();			
				}
			}
			long  roomsdays=(roomslidate.getTime()-roomszhudate.getTime())/(24*3600*1000)+1;
			
			//实体类封装了这些信息
			roomUserMsg=new RoomUserMsg(roomspeoples, roomsyouhuiprice,
					roomszhudate, roomslidate, roomsdays, 
					roomsgoodsprice, roomsyajin);
	
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("roomUserMsg", roomUserMsg);
		jsonText =JSONObject.fromObject(map);
//		System.out.println(jsonText.toString());
		return SUCCESS;
	}
	
}
