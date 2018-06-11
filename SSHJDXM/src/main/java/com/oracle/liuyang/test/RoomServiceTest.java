package com.oracle.liuyang.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Rooms;
import com.oracle.service.RoomService;
import com.oracle.service.RoomTypeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoomServiceTest {
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private RoomTypeService roomTypeService;

	


	public RoomTypeService getRoomTypeService() {
		return roomTypeService;
	}




	public void setRoomTypeService(RoomTypeService roomTypeService) {
		this.roomTypeService = roomTypeService;
	}




	public RoomService getRoomService() {
		return roomService;
	}




	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}




	@Test
	@Transactional
	@Rollback(true)
	public void testFindAll() {
		List<Rooms> roomlist= roomService.findAll(1);
		for (Rooms rooms : roomlist) {
			System.out.println(rooms.getRoomtypes().getRoomtype());
		}
	}

}
