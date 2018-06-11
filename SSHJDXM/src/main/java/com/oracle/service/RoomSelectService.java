package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.RoomselectDAO;
import com.oracle.dao.UsersDAO;
import com.oracle.entity.Roomselect;
import com.oracle.entity.Users;

@Service
public class RoomSelectService {
	
	@Autowired
	private RoomselectDAO  roomSelectDAO;
	
	public void setRoomSelectDAO(RoomselectDAO roomSelectDAO) {
		this.roomSelectDAO = roomSelectDAO;
	}
	@Autowired
	private UsersDAO  userDAO;	
	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/**
	 * 通过电话号码查询user,再查询预定信息
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Users  findReverseByPhone(String phone) throws Exception {
		
		List<Users> userList=userDAO.findByPhone(phone);
		if(userList.size()==0){
			throw new Exception("电话号码可能有误,请核对!");
			
		}
		Users users=userList.get(0);
		
		
		return users;
		
		
	}
	
	/*public List<Roomselect>  findReverseByPhone(String phone) throws Exception {
		
		List<Users> userList=userDAO.findByPhone(phone);
		if(userList.size()==0){
			throw new Exception("电话号码可能有误,请核对!");
			
		}
		Users users=userList.get(0);
		List<Roomselect> RoomselectList=roomSelectDAO.findByUser(users);
		
		return RoomselectList;
		
		
	}*/
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveRoomSelect(Roomselect roomSelect){
		roomSelectDAO.save(roomSelect);
		return true;
	}

}
