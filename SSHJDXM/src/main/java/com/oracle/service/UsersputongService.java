package com.oracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.dao.UsersputongDAO;
import com.oracle.entity.Usersputong;

@Service
public class UsersputongService {
	
	@Autowired
	private UsersputongDAO usersputongDAO;
	
	public UsersputongDAO getUsersputongDAO() {
		return usersputongDAO;
	}

	public void setUsersputongDAO(UsersputongDAO usersputongDAO) {
		this.usersputongDAO = usersputongDAO;
	}

	public Usersputong findByidCard(String idCard) {
		
		return usersputongDAO.findByIdcard(idCard,1);
	}

}
