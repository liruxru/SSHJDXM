package com.oracle.action.admin.qiantai;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.oracle.bean.RoomUserYuding;
import com.oracle.comm.SH;
import com.oracle.entity.Admins;
import com.oracle.entity.Customerorders;
import com.oracle.entity.Repair;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomselect;
import com.oracle.entity.Roomusers;
import com.oracle.entity.Users;
import com.oracle.entity.Usersputong;
import com.oracle.service.RoomSelectService;
import com.oracle.service.RoomService;
import com.oracle.service.RoomTypeService;
import com.oracle.service.UsersputongService;

/**
 * 前台管理员操作
 */

@Controller
@Namespace("/admin/qiantai")
@ParentPackage("json-default")
public class AdminAction  extends ActionSupport{
	//接收参数
	private Rooms room;	
	private Repair repair;
	private JSONObject jsonText;
	@Autowired//自动注入
	private RoomService roomService;
	@Autowired
	private RoomTypeService roomTypeService;
	@Autowired
	private UsersputongService  usersputongService;

	public Repair getRepair() {
		return repair;
	}
	public void setRepair(Repair repair) {
		this.repair = repair;
	}
	public JSONObject getJsonText() {
		return jsonText;
	}
	public void setJsonText(JSONObject jsonText) {
		this.jsonText = jsonText;
	}
	public Rooms getRoom() {
		return room;
	}
	public void setRoom(Rooms room) {
		this.room = room;
	}
	private String idCard;	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setRoomSercice(RoomService roomService) {
		this.roomService = roomService;
	}
	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}
	public UsersputongService getUsersputongService() {
		return usersputongService;
	}
	public void setUsersputongService(UsersputongService usersputongService) {
		this.usersputongService = usersputongService;
	}
	/**
	 * 修改房间属性,状态,类型
	 */
	@Action(value="alterRoomStatus",results={
			@Result(name=ActionSupport.SUCCESS,type="json",params={"root","0"}),
			@Result(name=ActionSupport.NONE,type="json",params={"root","1"})
	})
	public String alterRoomStatus(){
		boolean bln=roomService.edit(room,repair);
		if(bln){
			
			return ActionSupport.SUCCESS;
		}
		return ActionSupport.NONE;
	}
	
	/**
	 * 查看所有房间，房间状态，预定情况
	 */
	@Action(value="findAll",results={
			@Result(name=ActionSupport.SUCCESS,location="/page/admin/qiantai/list.jsp")
	})
	public String findAll(){
		ActionContext.getContext().put("roomtypeList", roomTypeService.findAll());
		ActionContext.getContext().put("roomList", roomService.findAll(1));
		return ActionSupport.SUCCESS;
	}
	
	//根据房间状态查看所有房间
	@Action(value="findAllByStatues",results={
			@Result(name="success",location="/page/admin/home.jsp")
	})
	public String findAllByStatues(){
		ActionContext.getContext().getSession().remove("countRooms");
		ActionContext.getContext().getSession().put("countRooms", roomService.roomsCount());
		ActionContext.getContext().put("mapListRooms", roomService.findAllByStatues(1,room.getRoomstatus().getId()));
		return "success";
	}
	//根据房间类型查看所有房间
	@Action(value="findAllByTypes",results={
			@Result(name="success",location="/page/admin/home.jsp")
	})
	public String findAllByTypes(){
		ActionContext.getContext().getSession().remove("countRooms");
		ActionContext.getContext().getSession().put("countRooms", roomService.roomsCount());
		ActionContext.getContext().put("mapListRooms", roomService.findAllByTypes(1,room.getRoomtypes().getId()));
		return "success";
	}
	
	/**
	 * 查看今晚所有入住用户信息和房间信息
	 */
	@Action(value="findAllRoomUser",results={
			@Result(name=SUCCESS,location="/page/admin/police/listTonight.jsp")
	})
	public String findAllRoomUser(){
		List<Rooms>  roomList =roomService.findAllUser();
		ActionContext.getContext().put("roomList", roomList);
		return SUCCESS;
	}
	/**
	 * 查看用户在本店入住信息
	 */
	@Action(value="findUserByIdCard",results={
			@Result(name=SUCCESS,location="/page/admin/police/listUser.jsp")
	})
	public String findUserByIdCard(){
		Usersputong  usersputong= usersputongService.findByidCard(idCard);
		ActionContext.getContext().put("usersputong", usersputong);
		return SUCCESS;
	}
	
	
	@Action(value="roomUserMsg",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String roomUserMsg(){
		Rooms rooms=roomService.findById(room.getId(), true);
		RoomUserMsg roomUserMsg=null;
//		System.out.println("第一次查哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"+rooms);
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
			//获取房间当前预定信息
			
		/*	jsonText=JSONObject.fromObject(roomUserMsg);
			System.out.println(jsonText.toString());*/
		}
		//获取预定信息的list集合
		List<RoomUserYuding> roomUserYudingList=new ArrayList<RoomUserYuding>();
		rooms=roomService.findRoomSelectByRoomId(room.getId());
//		System.out.println("第er次查哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈"+rooms);
		try {
			Set<Roomselect> roomselectSet=rooms.getRoomselects();
			RoomUserYuding roomUserYuding=null;
			for (Roomselect roomselect : roomselectSet) {
				String roomUserYudingName=roomselect.getUsers().getLoginname();
				if (roomselect.getUsers().getUsersputong()!=null) {
					roomUserYudingName=roomselect.getUsers().getUsersputong().getName();
				}
				
				String roomUserYudingPhone=roomselect.getUsers().getPhone();
				//获取预定住店日期
				Timestamp  roomUserYudingcreateDate=roomselect.getCreatedate();
				Timestamp  roomUserYudingendDate=roomselect.getEnddate();
				roomUserYuding=new RoomUserYuding(roomUserYudingName, roomUserYudingPhone,
								roomUserYudingcreateDate, roomUserYudingendDate);
				roomUserYudingList.add(roomUserYuding);				
			}
//			System.out.println(JSONArray.fromObject(roomUserYudingList).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("roomUserMsg", roomUserMsg);
		map.put("roomUserYudingList", roomUserYudingList);
		jsonText =JSONObject.fromObject(map);
//		System.out.println(jsonText.toString());
		return SUCCESS;
	}
	/*****************************************************************************/
	
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
	@Autowired//处理预定
	private RoomSelectService roomSelectService; 
	public void setRoomSelectService(RoomSelectService roomSelectService) {
		this.roomSelectService = roomSelectService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	/**
	 * 通过电话号码查找用户预定,并且封装预定信息
	 * @return
	 */
	@Action(value="findReverseByPhone",results={
		@Result(name=SUCCESS,location="/page/admin/qiantai/useryudingin.jsp"),
		@Result(name=ERROR,type="chain",params={"namespace","/admin","actionName","home"})
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
	
	/**
	 * 办理用户入住
	 * 1.改变房间状态从预定到入住
	 * 2.创建roomUSers表，这个表存储了当天在住人的信息
	 * 3.通过user1查找用户普通，没有创建绑定 。 user2查找，没有创建
	 * 
	 */
	
	@Action(value="transRoomIn",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/admin/room","actionName","findAll"}),
			@Result(name=ERROR,location="/page/admin/qiantai/reverse.jsp"),
		})
	public String transRoomIn(){
		//2018.1.2存储支出表
		Admins admin=(Admins) ActionContext.getContext().getSession().get(SH.SESSION_ADMIN_KEY);
		boolean bln=roomService.transUserInRoom(userFir,userTwo,useraId,roomId,deposit,roomSelect,outDate,admin);
		if(bln){
			return SUCCESS;
		}
		ActionContext.getContext().put("errorUserIn", "系统出错,稍后再试或者联系管理员");

		return ERROR;		
		
	}
	/**
	 *通过id获取room信息，给用户开房
	 */
	@Action(value="getRoomById",results={
			@Result(name=SUCCESS,location="/page/admin/qiantai/userin.jsp")
		})
	public String getRoomById(){
		Rooms room=roomService.findById(roomId);
		ActionContext.getContext().put("room", room);
		return SUCCESS;
		
	}
	
	/**
	 * 通过身份证查询是否有用户是会员
	 * @return
	 */
	@Action(value="searchUser1",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String searchUser1(){
		Users user1=null;
		if(userFir!=null&&!"".equals(userFir.getIdcard())){
			user1=roomService.findUser(userFir.getIdcard());
		}
		user1=roomService.findUser(userFir.getIdcard());
		if(user1!=null){
			user1.getUsersvip().setUserses(null);
		}
		jsonText=JSONObject.fromObject(user1);
		System.out.println(jsonText.toString());
		return SUCCESS;
	}
	@Action(value="searchUser2",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String searchUser2(){
		Users user2=null;
		if(userTwo!=null&&!"".equals(userTwo.getIdcard())){
			user2=roomService.findUser(userTwo.getIdcard());
		}
		if(user2!=null){
			user2.getUsersvip().setUserses(null);
		}
		jsonText=JSONObject.fromObject(user2);
		System.out.println(jsonText.toString());
		return SUCCESS;
	}
	
	/**
	 * 通过电话号用户是会员
	 * @return
	 */
	@Action(value="searchUserByPhone",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonText"})
	})
	public String searchUserByPhone(){
		Users userVo=roomService.findUserByPhone(phone);	
		try {
			if(userVo!=null){
				userVo.getUsersvip().setUserses(null);
				userVo.getUsersputong().setUserses(null);
				userVo.getUsersputong().getSex().setUsersputongs(null);
			}
			jsonText=JSONObject.fromObject(userVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 为没有预定的客户办理入住
	 * 1.获取用户身份证,存储用户表
	 * 2.存储roomuser表
	 */
	@Action(value="transRoomInNoReverse",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/admin","actionName","home"}),
			@Result(name=ERROR,location="/page/admin/qiantai/noReverse.jsp")	
	})
	public String transRoomInNoReverse(){
		Admins admin=(Admins) ActionContext.getContext().getSession().get(SH.SESSION_ADMIN_KEY);
		boolean bln=roomService.transUserInRoom(userFir,userTwo,useraId,userbId,roomId,deposit,price,outDate,admin);
		if(bln){
			return SUCCESS;
		}
		
		
		ActionContext.getContext().put("errorUserIn", "系统出错,稍后再试或者联系管理员");
		return ERROR;		
		
				
	}
	
	/*********************************************************************************/
	
	
	
	@Action(value="userOut",results={
			@Result(name=SUCCESS,location="/page/admin/qiantai/userout.jsp")
	})
	public String userOut(){
		//通过房间id查询房间
		Rooms room=roomService.findById(roomId,true);
		ActionContext.getContext().getSession().remove("room");
		if(room!=null){
			ActionContext.getContext().put("room", room);
		}
				
		return SUCCESS;		
	}
	
	

	/**
	 *刘洋:用户离店操作
	 *1.修改房间订单状态为4
	 *2.删除商品订单
	 *3.清空roomusers
	 *4.生成userOrders
	 * @return
	 */
	private BigDecimal zongji;
	public BigDecimal getZongji() {
		return zongji;
	}
	public void setZongji(BigDecimal zongji) {
		this.zongji = zongji;
	}
	@Action(value="transRoomOut",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/admin","actionName","home"})
	})
	public String transRoomOut(){
		//通过房间id查询房间
		Admins admin=(Admins) ActionContext.getContext().getSession().get(SH.SESSION_ADMIN_KEY);
		if(roomService.transRoomOut(roomId,admin,zongji)){
			return SUCCESS;	
		}
		return ERROR;
	}
	
}
