package com.oracle.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.oracle.dao.GoodstypesDAO;
import com.oracle.entity.Goodstypes;

@Service("typeService")
public class TypeService {
	@Resource(name="GoodstypesDAO")
	private GoodstypesDAO goodstypesDAO;

	public GoodstypesDAO getGoodstypesDAO() {
		return goodstypesDAO;
	}
	public void setGoodstypesDAO(GoodstypesDAO goodstypesDAO) {
		this.goodstypesDAO = goodstypesDAO;
	}
	
	public List<Goodstypes> list(){
		List<Goodstypes> ls= goodstypesDAO.findAll();
		return ls;
		
	}
	

}
