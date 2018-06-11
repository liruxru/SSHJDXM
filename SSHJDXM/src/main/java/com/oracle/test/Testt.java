package com.oracle.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.service.RoomService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Testt {
	@Autowired
	private RoomService rs;
	
	public RoomService getRs() {
		return rs;
	}

	public void setRs(RoomService rs) {
		this.rs = rs;
	}
	@Test
	@Transactional
	@Rollback(true)
	public void test(){
		rs.findAlllv(1);
		System.out.println("end");
	}
}
