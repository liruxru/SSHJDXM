package com.oracle.test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.entity.Roomselect;
import com.oracle.service.RoomSelectService;
import com.oracle.service.RoomService;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class FANGTest {
	@Autowired
	
	private RoomSelectService rsl;

	public RoomSelectService getRoomService() {
		return rsl;
	}

	public void setRoomService(RoomSelectService roomService) {
		this.rsl = roomService;
	}
	
	@Test
	@Rollback(true)
	public void test01(){
		Roomselect roomselect=new Roomselect();
		roomselect.setCreatedate(new Timestamp(new Date().getTime()));
		roomselect.setPrice(new BigDecimal(12));
		rsl.saveRoomSelect(roomselect);
	}

}
