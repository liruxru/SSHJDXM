package com.oracle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.RoomtypesDAO;
import com.oracle.entity.Roomtypes;

@Service
public class RoomTypeService {
	
	@Autowired
	private RoomtypesDAO roomTypeDAO;

	public void setRoomTypeDAO(RoomtypesDAO roomTypeDAO) {
		this.roomTypeDAO = roomTypeDAO;
	}


	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	@Cacheable(value="typeCache")
	public List<Roomtypes> findAll() {	
		
		return roomTypeDAO.findAll();
	}
	
	
}
