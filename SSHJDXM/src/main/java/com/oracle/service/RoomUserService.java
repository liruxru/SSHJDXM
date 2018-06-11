package com.oracle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.bean.RoomUserMsg;
import com.oracle.dao.RoomusersDAO;

@Service
public class RoomUserService {
	private RoomusersDAO roomusersDAO;
	private RoomUserMsg roomUserMsg;
	
	public RoomUserMsg getRoomUserMsg() {
		return roomUserMsg;
	}
	public void setRoomUserMsg(RoomUserMsg roomUserMsg) {
		this.roomUserMsg = roomUserMsg;
	}
	public RoomusersDAO getRoomusersDAO() {
		return roomusersDAO;
	}
	public void setRoomusersDAO(RoomusersDAO roomusersDAO) {
		this.roomusersDAO = roomusersDAO;
	}
	
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//	public List<RoomUserMsg> roomUserMsg(){
//		List<RoomUserMsg> ls=roomusersDAO.roomUserMsg();
//		return ls;
//	}
	
}
