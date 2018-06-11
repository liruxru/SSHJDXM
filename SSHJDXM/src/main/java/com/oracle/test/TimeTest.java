package com.oracle.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.utils.TimeUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TimeTest {

	@Test
	@Transactional
	@Rollback(true)
	public void test(){
		String str="2018-01-05 to 2018-01-10";
		Timestamp tt=TimeUtils.getCxMaxzguce(str);
		System.out.println(tt);
}
}
