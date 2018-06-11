package com.oracle.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.bean.RoomsCount;
import com.oracle.dao.AdminsDAO;
import com.oracle.dao.AssetsDAO;
import com.oracle.dao.AssetsdetalDAO;
import com.oracle.dao.CustomercartDAO;
import com.oracle.dao.CustomerorderitemsDAO;
import com.oracle.dao.CustomerordersDAO;
import com.oracle.dao.RepairDAO;
import com.oracle.dao.RoomsDAO;
import com.oracle.dao.RoomselectDAO;
import com.oracle.dao.RoomusersDAO;
import com.oracle.dao.UserRoomOrderDAO;
import com.oracle.dao.UsersDAO;
import com.oracle.dao.UsersputongDAO;
import com.oracle.entity.Admins;
import com.oracle.entity.Assets;
import com.oracle.entity.Assetsdetal;
import com.oracle.entity.Customerorders;
import com.oracle.entity.Orderstatus;
import com.oracle.entity.Repair;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomselect;
import com.oracle.entity.Roomstatus;
import com.oracle.entity.Roomusers;
import com.oracle.entity.Sexs;
import com.oracle.entity.Staffs;
import com.oracle.entity.UserRoomOrder;
import com.oracle.entity.Users;
import com.oracle.entity.Usersputong;
import com.oracle.utils.SSHUtils;

@Service
public class RoomService {
	@Autowired
	private AssetsdetalDAO assetsdetalDAO;
	public AssetsdetalDAO getAssetsdetalDAO() {
		return assetsdetalDAO;
	}
	public void setAssetsdetalDAO(AssetsdetalDAO assetsdetalDAO) {
		this.assetsdetalDAO = assetsdetalDAO;
	}
	@Autowired
	private RoomselectDAO roomselectDAO ;	
	public void setRoomselectDAO(RoomselectDAO roomselectDAO) {
		this.roomselectDAO = roomselectDAO;
	}
	@Autowired
	private UsersputongDAO usersputongDAO ;
	public void setUsersputongDAO(UsersputongDAO usersputongDAO) {
		this.usersputongDAO = usersputongDAO;
	}
	@Autowired
	private UsersDAO userDAO;
	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}
	@Autowired
	private RoomsDAO roomsDAO;
	public void setRoomsDAO(RoomsDAO roomsDAO) {
		this.roomsDAO = roomsDAO;
	}
	@Autowired
	private RoomusersDAO roomusersDAO;
	public void setRoomusersDAO(RoomusersDAO roomusersDAO) {
		this.roomusersDAO = roomusersDAO;
	}
	

	@Autowired
	CustomerordersDAO 	customerordersDAO;
	public void setCustomerordersDAO(CustomerordersDAO customerordersDAO) {
		this.customerordersDAO = customerordersDAO;
	}
	//lv  房间数量统计
	public RoomsCount roomsCount(){
		Long allRooms=roomsDAO.allRooms();
		Long nullRooms=roomsDAO.countRooms(2);
		Long propleRooms=roomsDAO.countRooms(3);
		Long dirtyRooms=roomsDAO.countRooms(4);
		Long peopleDirtyRooms=roomsDAO.countRooms(5);
		Long yudingRooms=roomsDAO.countRooms(6);
		Long repairRooms=roomsDAO.countRooms(7);
		Long noMakeRooms=roomsDAO.countRooms(1);
		RoomsCount roomsCount=new RoomsCount(allRooms, nullRooms, propleRooms, dirtyRooms, peopleDirtyRooms, yudingRooms, repairRooms,noMakeRooms);
		return roomsCount;
	}

	//LV   按楼层查询房间
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<Integer,List<Rooms>>  findAlllv(Integer i) {
		Map<Integer, List<Rooms>> mp=new HashMap<Integer, List<Rooms>>();
 		List<Rooms> roomList=new ArrayList<Rooms>();
		for (; i < 7; i++) {
			roomList=roomsDAO.findAlllv(i);
			mp.put(i,roomList);
		}	
		return mp;
	}
	
	
	//返回个别楼层的房间
	/**
	 * 方文奇
	 * @param i
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Rooms>  findAllByPosiion(Integer i) {
 		List<Rooms> roomList=new ArrayList<Rooms>();
		roomList=roomsDAO.findAlllv(i);	
		return roomList;
	}
	//返回所有selectroom
	/**
	 * 方文奇
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Roomselect> findAllSelectRooms(){
		List<Roomselect> list=new ArrayList<Roomselect>();
		list=roomselectDAO.findAll();
		return list;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Rooms> listRoomsCanUse(Date startDate,Date endDate,Integer position){
		List<Roomselect> listSelect=findAllSelectRooms();
		List<Rooms> roomList=findAllByPosiion(position);
		List<Rooms> roomSelect=new ArrayList<Rooms>();
		//获取有冲突的房间
		for (Roomselect rooms : listSelect) {
			//预定时间和已被定的事件有交集
			if(!(endDate.before(rooms.getCreatedate()) || startDate.after(rooms.getEnddate()))){
				roomSelect.add(rooms.getRooms());
			}
		}
		//roomlist中出去有冲突的房间
		for (Rooms rooms : roomSelect) {
			roomList.remove(rooms); 
		}
		return roomList;
	}
	
	//根据状态查看房间
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<Integer,List<Rooms>>  findAllByStatues(Integer position,Integer roomstatus) {
		Map<Integer, List<Rooms>> mp=new HashMap<Integer, List<Rooms>>();
		List<Rooms> roomList=new ArrayList<Rooms>();
		for (; position < 7; position++) {
			roomList=roomsDAO.findAllByStatues(position,roomstatus);
			mp.put(position,roomList);
		}	
		return mp;
	}
	//根据状态查看房间
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Rooms>  findAllByStatues(Integer roomstatus) {
		return roomsDAO.findAllByStatues(roomstatus);
	}
	
	//根据类型查看房间
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Map<Integer,List<Rooms>>  findAllByTypes(Integer position,Integer roomstypes) {
		Map<Integer, List<Rooms>> mp=new HashMap<Integer, List<Rooms>>();
		List<Rooms> roomList=new ArrayList<Rooms>();
		for (; position < 7; position++) {
			roomList=roomsDAO.findAllByTypes(position,roomstypes);
			mp.put(position,roomList);
		}	
		return mp;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public List<Rooms> findAllByType(Integer typeId) {
		return roomsDAO.findAllByType(typeId);
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Rooms> findAll(Integer i) {
		List<Rooms> roomList=roomsDAO.findAll(i);
		return roomList;
	}
/**************************************************************************************/
	/**
	 * 修改房间属性,状态,类型
	 * @param room
	 * @param repair 
	 * @return
	 */
	@Autowired
	private RepairDAO repairDAO;
	public RepairDAO getRepairDAO() {
		return repairDAO;
	}
	public void setRepairDAO(RepairDAO repairDAO) {
		this.repairDAO = repairDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean edit(Rooms room, Repair repair) {
		boolean bln=false;		
		try {
			roomsDAO.alterRoomStatus(room);
			if(repair!=null){
				Repair repairVo=new Repair();
				repairVo.setDescription(repair.getDescription());
				repairVo.setRooms(room);
				repairVo.setCreatedate(new Timestamp(System.currentTimeMillis()));
				repairDAO.save(repairVo);
			}
			bln=true;
		} catch (Exception e) {
			bln=false;
			e.printStackTrace();
		}
		return bln;
	}

	

	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public  Rooms findById(Integer roomId) {		
		return roomsDAO.findById(roomId,1);
	}

	/**
	 * 通过用户身份证查询会员
	 * @param idcard
	 * @return
	 */
	/**
	 * @param idcard
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Users findUser(String idcard) {		
		Users users=new Users();

		try {
			List<Usersputong> userPutongs=usersputongDAO.findByIdcard(idcard);
			if(userPutongs.size()!=0){			
				Users user=userDAO.findByUserPutongId(userPutongs.get(0).getId());
				if(user!=null){
					users.setId(user.getId());
					users.setJifen(user.getJifen());
					users.setPhone(user.getPhone());
					users.setLoginname(user.getLoginname());
					users.setUsersvip(user.getUsersvip());
					Usersputong usersputong=new Usersputong();	
					usersputong.setId(userPutongs.get(0).getId());
					usersputong.setName(userPutongs.get(0).getName());
					Sexs sexs=new Sexs();
					sexs.setId(userPutongs.get(0).getSex().getId());
					usersputong.setSex(sexs);
					users.setUsersputong(usersputong);
					return users;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;
	}


	/**
	 * 办理预定用户入住
	 * 1.改变房间状态从预定到入住
	 * 2.创建roomUSers表，这个表存储了当天在住人的信息
	 * 3.通过user1查找用户普通，没有创建绑定 user2查找，没有创建
	 * @param userId 
	 * @param roomId 
	 * @param roomId2 
	 * @param deposit 
	 * @param roomSelect 
	 * @param admin 
	 * 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean transUserInRoom(Usersputong userFir, Usersputong userTwo,
			Integer user1Id, Integer roomId, BigDecimal deposit, Roomselect roomSelect,Date outDate, Admins admin) {
		someUserRuZhu(userFir, user1Id, roomId, deposit,outDate);
		someUserRuZhu(userTwo, null, roomId, deposit,outDate);
		
		//生成用户订单
		Rooms rooms=new Rooms();rooms.setId(roomId);
		Orderstatus orderstatus=new Orderstatus();  orderstatus.setId(3);
		roomSelect=roomselectDAO.findById(roomSelect.getId());
		Customerorders customerorders=new Customerorders(rooms, orderstatus, 
				userFir,SSHUtils.getOrderSn() , 
				roomSelect.getPrice(), new Timestamp(System.currentTimeMillis()), null, null);
		
		customerordersDAO.save(customerorders);		
		//删除用户预定信息
		roomSelect.setRooms(null);roomSelect.setUsers(null);
		roomselectDAO.delete(roomSelect);
		//生成财务报表
		creatAssets(admin, customerorders);
		//押金条目
		creatAssetsByDeposit(admin, deposit);
		
		return true;
	}



	/**
	 * 办理未预定用户入住
	 * 1.改变房间状态从预定到入住
	 * 2.创建roomUSers表，这个表存储了当天在住人的信息
	 * 3.通过user1查找用户普通，没有创建绑定 user2查找，没有创建
	 * @param userId 
	 * @param roomId 
	 * @param roomId2 
	 * @param deposit 
	 * @param price 
	 * @param outDate 
	 * @param admin 
	 * 
	 */
	@Autowired
	private AdminsDAO adminsDAO;
	public AdminsDAO getAdminsDAO() {
		return adminsDAO;
	}
	public void setAdminsDAO(AdminsDAO adminsDAO) {
		this.adminsDAO = adminsDAO;
	}
	@Autowired
	private AssetsDAO assetsDAO;
	public AssetsDAO getAssetsDAO() {
		return assetsDAO;
	}
	public void setAssetsDAO(AssetsDAO assetsDAO) {
		this.assetsDAO = assetsDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean transUserInRoom(Usersputong userFir, Usersputong userTwo,
			Integer user1Id, Integer user2Id, Integer roomId, BigDecimal deposit, BigDecimal price, Date outDate, Admins admin) {
		/*1.查看数据库是否存在这两个身份信息
		 * 2.不存在存储
		 * 3.存储之后，查看会员表是否绑定了身份证，没有绑定
		 * 4.修改房间状态，存储普通用户房间关系表
		 * */
		someUserRuZhu(userFir, user1Id, roomId, deposit,outDate);
		someUserRuZhu(userTwo, user2Id, roomId, deposit,outDate);
		
		//生成用户订单
		Rooms rooms=new Rooms();rooms.setId(roomId);
		Orderstatus orderstatus=new Orderstatus();  orderstatus.setId(3);
		Customerorders customerorders=new Customerorders(rooms, orderstatus, 
				userFir,SSHUtils.getOrderSn() , 
				price, new Timestamp(System.currentTimeMillis()), null, null);
		
		customerordersDAO.save(customerorders);	
		//生成财务报表
		creatAssets(admin, customerorders);
		//押金条目
		creatAssetsByDeposit(admin, deposit);
		
		return true;
		
	}
	/**
	 * 通过退款办理财务明细
	 */
	private synchronized  void creatAssetsByTuikuan(Admins admin, BigDecimal zongji) {
		// TODO Auto-generated method stub
		Assets assetsDo=assetsDAO.getLastAssets();
		Assets assetsVo=new Assets();
		System.out.println(zongji);
		//新建财务总表
		assetsVo.setSum(assetsDo.getSum().subtract(zongji));
		assetsVo.setCreatedate( new Timestamp(System.currentTimeMillis()));
		Staffs staffs=new Staffs();
		Admins adminDo=adminsDAO.findById(admin.getId());
		staffs.setId(adminDo.getStaffs().getId());
		Assetsdetal assetsdetal=new Assetsdetal(staffs,SSHUtils.getAssetSn(), 
				null, zongji,assetsVo.getSum(), new Timestamp(System.currentTimeMillis()));
		assetsdetalDAO.save(assetsdetal);
		assetsDAO.save(assetsVo);
	}
	private synchronized  void creatAssetsByDeposit(Admins admin, BigDecimal deposit) {
		//生成财务明细
		Assets assetsDo=assetsDAO.getLastAssets();
		Assets assetsVo=new Assets();
		//新建财务总表
		assetsVo.setSum(assetsDo.getSum().add(deposit));
		assetsVo.setCreatedate( new Timestamp(System.currentTimeMillis()));
		Staffs staffs=new Staffs();
		Admins adminDo=adminsDAO.findById(admin.getId());
		staffs.setId(adminDo.getStaffs().getId());
		Assetsdetal assetsdetal=new Assetsdetal(staffs,SSHUtils.getAssetSn(), 
				deposit, null,assetsVo.getSum(), new Timestamp(System.currentTimeMillis()));
		assetsdetalDAO.save(assetsdetal);
		assetsDAO.save(assetsVo);
		
	}
	//生成财务明细,确保线程安全
	private synchronized void  creatAssets(Admins admin, Customerorders customerorders) {
		//生成财务明细
		Assets assetsDo=assetsDAO.getLastAssets();
//		assets.setSum(assets.getSum().add(customerorders.getSum()));
		Assets assetsVo=new Assets();
		//新建财务总表
		assetsVo.setSum(assetsDo.getSum().add(customerorders.getSum()));
		assetsVo.setCreatedate( new Timestamp(System.currentTimeMillis()));
		Staffs staffs=new Staffs();
		Admins adminDo=adminsDAO.findById(admin.getId());
		staffs.setId(adminDo.getStaffs().getId());
		Assetsdetal assetsdetal=new Assetsdetal(staffs,SSHUtils.getAssetSn(), 
				customerorders.getSum(), null,assetsVo.getSum(), new Timestamp(System.currentTimeMillis()));
		assetsdetalDAO.save(assetsdetal);
		assetsDAO.save(assetsVo);
	
	}

	//办理用户入住
	private void someUserRuZhu(Usersputong userFir, Integer user1Id,
			Integer roomId, BigDecimal deposit, Date outDate) {
		if(userFir!=null&&!"".equals(userFir.getIdcard().trim())){
			Users users1=null;
			if(user1Id!=null){
				users1=userDAO.findById(user1Id);
				
			}
			List<Usersputong> user1List=usersputongDAO.findByIdcard(userFir.getIdcard());
			if(user1List.size()==0){
				usersputongDAO.save(userFir);
				
			}else{
				BeanUtils.copyProperties(user1List.get(0), userFir);
				
			}
			//如果用户为会员，并且没有绑定身份证信息，进行绑定
			if(users1!=null&&users1.getUsersputong()==null){
				users1.setUsersputong(userFir);
			}
			
			// 2.创建roomUSers表，这个表存储了当天在住人的信息
			//通过roomId查找Room
			Rooms room=roomsDAO.findById(roomId);
			Roomusers roomUsers1=new  Roomusers(room ,userFir,
					new Timestamp(System.currentTimeMillis()),
					new Timestamp(outDate.getTime()+14*3600*1000));
			roomusersDAO.save(roomUsers1);
			//3.改变房间状态从预定到入住,存押金
			Roomstatus statu=new Roomstatus();			
			statu.setId(4);
			room.setRoomstatus(statu);
			room.setDeposit(deposit);
			
		}
	}
	/**
	 * 通过电话号码查询用户
	 * @param phone
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Users findUserByPhone(String phone) {
		Users userVo=null;
		try {
			userVo = new Users();
			Sexs sex=new Sexs();
			Usersputong usersputong=new Usersputong();
			Users userDo=userDAO.findByPhone(phone,1);
			if(userDo!=null){
				userVo.setJifen(userDo.getJifen());
				userVo.setPhone(userDo.getPhone());
				userVo.setLoginname(userDo.getLoginname());
				userVo.setUsersvip(userDo.getUsersvip());
				sex.setId(userDo.getUsersputong().getSex().getId());
				usersputong.setSex(sex);
				usersputong.setIdcard(userDo.getUsersputong().getIdcard());
				usersputong.setName(userDo.getUsersputong().getName());
				userVo.setUsersputong(usersputong);				
				return userVo;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userVo;
	}
	/**
	 * 用户退房时查询房间内用户
	 * @param roomId
	 * @param b
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Rooms findById(Integer roomId, boolean b) {
		
		return roomsDAO.findById(roomId,b);
	}
	
	/**
	 *刘洋:用户离店操作
	 *1.修改房间订单状态为4
	 *2.删除商品订单
	 *3.清空roomusers
	 *4.生成userOrders
	 * @return
	 * 
	 */
	@Autowired
	private CustomercartDAO  customercartDAO;	
	public void setCustomercartDAO(CustomercartDAO customercartDAO) {
		this.customercartDAO = customercartDAO;
	}
	@Autowired
	private UserRoomOrderDAO userRoomOrderDAO;
	
	
	
	public UserRoomOrderDAO getUserRoomOrderDAO() {
		return userRoomOrderDAO;
	}
	public void setUserRoomOrderDAO(UserRoomOrderDAO userRoomOrderDAO) {
		this.userRoomOrderDAO = userRoomOrderDAO;
	}
	@Autowired
	private CustomerorderitemsDAO  customerorderitemsDAO;
	
	public CustomerorderitemsDAO getCustomerorderitemsDAO() {
		return customerorderitemsDAO;
	}
	public void setCustomerorderitemsDAO(CustomerorderitemsDAO customerorderitemsDAO) {
		this.customerorderitemsDAO = customerorderitemsDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)	
	public boolean transRoomOut(Integer roomId, Admins admin, BigDecimal zongji) {
		
		try {
			//清空房间购物车
			customercartDAO.deleteByRoomId(roomId);
			//修改房间商品订单状态,修改收获订单为结算，删除未收获订单
			List<Customerorders>  customerorderss=customerordersDAO.findGoodsOrderByRoomIdAndStatus(roomId, 1);
			for (Customerorders customerorders : customerorderss) {
				Orderstatus orderstatus=new Orderstatus();
				orderstatus.setId(2);
				customerorders.setOrderstatus(orderstatus);
			/*	//生成财务报表
				creatAssets(admin, customerorders);*/
			}
			creatAssetsByTuikuan(admin, zongji);
			
			
			//获取订单状态为5的订单详情
			Customerorders customerorders1=customerordersDAO.findByRoomIdAndStatus(roomId,5);
			//删除这个订单下的订单详情
			if(customerorders1!=null){
				customerorderitemsDAO.deleteByOrder(customerorders1.getId());
				customerordersDAO.deleteGoodOrderByRoomId(roomId,5);
			}
		
		
			
			//通过房间id获取房间订单
			Customerorders customerorders=customerordersDAO.findByRoomIdAndStatus(roomId,3);
			//设置离店时间
			customerorders.setModitfydate(new Timestamp(System.currentTimeMillis()));
			//清空roomuser,通过房间id查找roomuser
			List<Roomusers> roomusersList=roomusersDAO.findByRoomId(roomId);
			for (Roomusers roomusers : roomusersList) {
				//生成UserRoomOrder		
				UserRoomOrder userRoomOrder=new UserRoomOrder(customerorders, roomusers.getUsersputong());
				//设置入店日期
				userRoomOrder.setCreatedate(roomusers.getCreatedate());
				//设置结账日期
				userRoomOrder.setModitfydate(new Timestamp(System.currentTimeMillis()));
				//保存
				userRoomOrderDAO.save(userRoomOrder);
				roomusers.getUsersputong();
				roomusers.setRooms(null);
				roomusers.setUsersputong(null);
				//清空roomuser
				roomusersDAO.delete(roomusers);
			}		
			
			//修改房间订单状态
			customerordersDAO.editRoomOrderByRoomId(roomId,4);
			//修改房间状态
			Rooms room=roomsDAO.findById(roomId);
			Roomstatus roomstatus=new Roomstatus();
			//房间已退，未清理
			roomstatus.setId(3);
			//设置押金为0
			room.setDeposit(new BigDecimal(0));
			room.setRoomstatus(roomstatus);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	public List<Rooms> findAllUser() {
		
		return roomsDAO.findAllUser();
	}
	public Rooms findRoomSelectByRoomId(Integer id) {
		Rooms rooms=roomsDAO.findRoomSelectByRoomId(id);
		return  rooms;
	}

}
