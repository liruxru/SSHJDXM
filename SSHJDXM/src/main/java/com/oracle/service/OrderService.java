package com.oracle.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.CustomercartDAO;
import com.oracle.dao.CustomerorderitemsDAO;
import com.oracle.dao.CustomerordersDAO;
import com.oracle.dao.GoodsDAO;
import com.oracle.entity.Customercart;
import com.oracle.entity.Customerorderitems;
import com.oracle.entity.Customerorders;
import com.oracle.entity.Goods;
import com.oracle.entity.Orderstatus;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomusers;
import com.oracle.entity.Users;
import com.oracle.utils.SSHUtils;

@Service
public class OrderService {
	
	@Autowired
	private CustomerordersDAO customerordersDAO;
	@Autowired
	private CustomercartDAO  customercartDAO;
	@Autowired
	private CustomerorderitemsDAO customerorderitemsDAO;
	@Autowired
	private GoodsDAO goodsDAO;
	
	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	public CustomerorderitemsDAO getCustomerorderitemsDAO() {
		return customerorderitemsDAO;
	}

	public void setCustomerorderitemsDAO(CustomerorderitemsDAO customerorderitemsDAO) {
		this.customerorderitemsDAO = customerorderitemsDAO;
	}

	public CustomercartDAO getCustomercartDAO() {
		return customercartDAO;
	}

	public void setCustomercartDAO(CustomercartDAO customercartDAO) {
		this.customercartDAO = customercartDAO;
	}

	public CustomerordersDAO getCustomerordersDAO() {
		return customerordersDAO;
	}

	public void setCustomerordersDAO(CustomerordersDAO customerordersDAO) {
		this.customerordersDAO = customerordersDAO;
	}
	
	/**
	 * 刘洋通过房间id创建订单创建订单：计算总价,生成订单,生成订单详情，清空购物车，维护库存
	 * @param cartId
	 * @param user 
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean creatOrder(Integer[] cartId, Rooms room) {

		try {
			
			
			BigDecimal sum=new BigDecimal("0.00");
			List<Customercart> cartList=new ArrayList<Customercart>();
			for (Integer id : cartId) {
				Customercart cart=customercartDAO.findById(id);
				sum=sum.add(cart.getTotalsum());
				cartList.add(cart);
			}
			Orderstatus statu=new Orderstatus();
			statu.setId(5);
			//生成购物订单
			Customerorders  customerorders=new Customerorders(room,statu , null, 
					SSHUtils.getOrderSn(), sum, new  Timestamp(System.currentTimeMillis()), null, null);
			customerordersDAO.save(customerorders);
			
			Set<Customerorderitems>  CustomerorderitemsSet=new HashSet<Customerorderitems>();
			//存储订单详情
			for (Customercart customercart : cartList) {
				Customerorderitems  customerorderitems=new Customerorderitems(null, customerorders, 
						customercart.getGoods(), customercart.getGoodsnum(),
						customercart.getGoods().getPrice(), customercart.getTotalsum());
				customerorderitemsDAO.save(customerorderitems);
				CustomerorderitemsSet.add(customerorderitems);
				//清空购物车维护
				Goods goods=goodsDAO.findById(customercart.getGoods().getId());
				goods.setNum(goods.getNum()-customercart.getGoodsnum());
				customercartDAO.deleteById(customercart.getId());
				
				
			}
			customerorders.setCustomerorderitemses(CustomerorderitemsSet);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return false;
		
	}

	/**
	 * 刘洋通过房间浏览订单
	 * @param room
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Customerorders> listGoodsOrderByRoom(Rooms room) {		
		return customerordersDAO.listGoodsOrderByRoom(room);
	}
	
	/**********************************************************************************/
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean creatOrder(Rooms room) {
		try {		
			BigDecimal sum=new BigDecimal("0.00");
			List<Customercart> cartList=customercartDAO.findByRoom(room.getId());
			for (Customercart customercart : cartList) {
				sum=sum.add(customercart.getTotalsum());
				
			}
			Orderstatus statu=new Orderstatus();
			statu.setId(5);
			//生成购物订单
			Customerorders  customerorders=new Customerorders(room,statu , null, 
					SSHUtils.getOrderSn(), sum, new  Timestamp(System.currentTimeMillis()), null, null);
			customerordersDAO.save(customerorders);
			Set<Customerorderitems>  CustomerorderitemsSet=new HashSet<Customerorderitems>();
			//存储订单详情
			for (Customercart customercart : cartList) {
				Customerorderitems  customerorderitems=new Customerorderitems(null, customerorders, 
						customercart.getGoods(), customercart.getGoodsnum(),
						customercart.getGoods().getPrice(), customercart.getTotalsum());
				customerorderitemsDAO.save(customerorderitems);
				CustomerorderitemsSet.add(customerorderitems);
				//清空购物车维护
				Goods goods=goodsDAO.findById(customercart.getGoods().getId());
				goods.setNum(goods.getNum()-customercart.getGoodsnum());
				customercartDAO.deleteById(customercart.getId());
			}
			customerorders.setCustomerorderitemses(CustomerorderitemsSet);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*************************************************/
	/**
	 * 修改订单状态
	 * 创建订单
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean alertOrder(Integer orderId) {
		Customerorders  customerorders=customerordersDAO.findById(orderId);
		Orderstatus orderstatus=new Orderstatus();
		orderstatus.setId(1);
		customerorders.setOrderstatus(orderstatus);
		return true;
	}
	
	
}
