package com.oracle.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.bean.Address;
import com.oracle.bean.Page;
import com.oracle.bean.Search;
import com.oracle.dao.RoomsDAO;
import com.oracle.dao.UsersDAO;
import com.oracle.dao.UsersvipDAO;
import com.oracle.entity.Rooms;
import com.oracle.entity.Users;
import com.oracle.entity.Usersvip;
import com.oracle.utils.TimeUtils;

@Service
public class UserService {
	
	@Autowired
	private UsersDAO userDAO;
	@Autowired
	private RoomsDAO roomsDAO;
	@Autowired
	private UsersvipDAO usersvipDAO;
	
	
	public UsersvipDAO getUsersvipDAO() {
		return usersvipDAO;
	}
	public void setUsersvipDAO(UsersvipDAO usersvipDAO) {
		this.usersvipDAO = usersvipDAO;
	}
	public void setUserDAO(UsersDAO userDAO) {
		this.userDAO = userDAO;
	}
	public UsersDAO getUserDAO() {
		return userDAO;
	}
	public void setRoomsDAO(RoomsDAO roomsDAO) {
		this.roomsDAO = roomsDAO;
	}
	public RoomsDAO getRoomsDAO() {
		return roomsDAO;
	}

	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public boolean login(Users user) throws Exception{
		try {
			List<Users> userList=userDAO.findByLoginname(user.getLoginname(),1);
			if(userList.size()==0)return false;
			if(!userList.get(0).getPassword().equals(user.getPassword()))return false;
			Users userDo=userList.get(0);
			BeanUtils.copyProperties(userDo, user);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		}
		
	}
	
	/**
	 * 通过房间no获取房间信息
	 * @param no
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Rooms findRoomByNo(String no) {
		
		
		return roomsDAO.findByNo(no).isEmpty()?null:roomsDAO.findByNo(no).get(0);
	}
	
	/**
	 * 查询全部用户
	 * @param pageSize 
	 * @param currentPage 
	 * @param search 
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page finAll(Integer currentPage, Integer pageSize, Search search) {
		
		if (search!=null) {
			if (search.getCxtime()!=null && !"".equals(search.getCxtime())) {
				String strCxtime=search.getCxtime();
				Timestamp cxminzhuce=TimeUtils.getCxminzhuce(strCxtime);
				Timestamp cxmaxzhuce=TimeUtils.getCxMaxzguce(strCxtime);
				search.setCxminzhuce(cxminzhuce);
				search.setCxmaxzhuce(cxmaxzhuce);
			}
		}
		//先记录总记录数
		Integer count=userDAO.countId(search);
		//总页数
		Integer totalPage=Page.getTotalPage(count, pageSize);
		//当前记录数
		Integer offset = Page.getOffset(currentPage, pageSize);
		//每次取得长度
		Integer length = Page.length(pageSize);
		
		List<Users> listUsers=userDAO.findAll(offset,length,search);
		Page page=new Page(count, totalPage, currentPage, listUsers);
		return page;
	}
	
	/**
	 * 验证用户登录名是否已存在
	 * @param loginName
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean checkUserName(String loginName) {
		if (userDAO.findByLoginname(loginName).size()==0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 添加/修改会员
	 * @param user
	 * @param address
	 * @return 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean addUsers(Users user, Address address) {
		if (!address.getSheng().equals("0")){
			String p=address.getSheng().substring(0, address.getSheng().lastIndexOf("|"));
			String s=address.getShi().substring(0, address.getShi().lastIndexOf("|"));
			String q=address.getQu().substring(0, address.getQu().lastIndexOf("|"));
			String add=p+s+q;
			user.setAddress(add);
		}
		
		if (user.getId()!=null) {
			Users usersDo=userDAO.findById(user.getId());
			usersDo.setAddress(user.getAddress());
			usersDo.setUsername(user.getUsername());
			usersDo.setAge(user.getAge());
			usersDo.setLoginname(user.getLoginname());
			usersDo.setPassword(user.getPassword());
			usersDo.setPhone(user.getPhone());
			usersDo.setModitfydate(new Timestamp(new Date().getTime()));
			usersDo.setEmail(user.getEmail());
			return true;
		}else {
			Usersvip usersvip=usersvipDAO.findByVipname("V0").get(0);
			user.setCreatedate(new Timestamp(new Date().getTime()));
			user.setNum(0);
			user.setJifen(0l);
			user.setUsersvip(usersvip);
			userDAO.save(user);
			return true;
		}
		
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Users findUserById(Integer id) {
		Users user=userDAO.findById(id);
		return user;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void delete(Integer id) {
		Users user=userDAO.findById(id);
		userDAO.delete(user);
	}
	
	

}
