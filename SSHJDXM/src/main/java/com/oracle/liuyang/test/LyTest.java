package com.oracle.liuyang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.oracle.dao.TESTRoomsDAO;
import com.oracle.entity.Rooms;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LyTest {
	
	@Autowired
	private  TESTRoomsDAO t;
	
	public TESTRoomsDAO getT() {
		return t;
	}

	public void setT(TESTRoomsDAO t) {
		this.t = t;
	}

	@Test
	public void m() {
		
		Rooms room=t.findById1(2,true);
	
		
	}
	/**
	 *入住人，入住日期，预期离开，   房间押金，住宿价格，房间订单价格   
	 */
	

}
