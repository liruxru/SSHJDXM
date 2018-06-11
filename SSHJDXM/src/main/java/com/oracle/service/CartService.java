package com.oracle.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.dao.CustomercartDAO;
import com.oracle.dao.GoodsDAO;
import com.oracle.entity.Customercart;
import com.oracle.entity.Goods;
import com.oracle.entity.Rooms;
import com.oracle.entity.Roomusers;
import com.oracle.entity.Users;

@Service
public class CartService {
	
	@Autowired
	private CustomercartDAO CustomercartDAO;
	public void setCustomercartDAO(CustomercartDAO customercartDAO) {
		CustomercartDAO = customercartDAO;
	}
	@Autowired
	private GoodsDAO goodsDAO;	
	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}

	/**
	 * 刘洋通过房间和商品获得购物车
	 * @param goods
	 * @param room
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean addToCart(Goods goods, Rooms room) {
		/**
		 * 1.添加商品到购物车，首先查看购物车中是否存在这个用户这个商品的购物车
		 * 2.如果购物车为空，新建购物车
		 * 3.如果购物车不为空，购物车数量+1
		 * 
		 */
		
		Goods good=goodsDAO.findById(goods.getId());
		Customercart  customercart=CustomercartDAO.findByRoomAndGoods(goods,room);
		if(customercart==null){
			
			customercart=new Customercart(room,null , goods, good.getName(), 1, good.getPrice(), new Timestamp(System.currentTimeMillis()), null);
			CustomercartDAO.save(customercart);
			return true;
		}
		customercart.setGoodsnum(customercart.getGoodsnum()+1);
		customercart.setTotalsum(customercart.getTotalsum().add(good.getPrice()));
		customercart.setModitfydate(new Timestamp(System.currentTimeMillis()));
		return true;		
		
	}
	
	

	/**
	 * 通过购物车id移除购物车
	 * @param cart
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean remove(Customercart cart) {
		try {
			CustomercartDAO.delete(cart);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
	
	
	
	/**
	 * 通过房间查询购物车
	 * @param id
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<Customercart> roomCart(Integer id) {
		List<Customercart> cartListDo=CustomercartDAO.findByRoom(id);
		
		List<Customercart> cartListVo=new ArrayList<Customercart>();
		for (Customercart customercartDo : cartListDo) {
			Customercart customercartVo=new Customercart();
			Goods goods=new Goods();
			customercartVo.setId(customercartDo.getId());
			customercartVo.setTotalsum(customercartDo.getTotalsum());
			customercartVo.setGoodsname(customercartDo.getGoodsname());
			customercartVo.setGoodsnum(customercartDo.getGoodsnum());
			goods.setImgcover(customercartDo.getGoods().getImgcover());
			goods.setId(customercartDo.getGoods().getId());
			goods.setPrice(customercartDo.getGoods().getPrice());
			customercartVo.setGoods(goods);
			cartListVo.add(customercartVo);
		}
		return cartListVo;
	}

	/**********************************************************************************/
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean alertCart(Customercart cart, String tag) {
		//获取购物车
		Customercart cartDo=CustomercartDAO.findById(cart.getId());
		if("add".equals(tag)){
			cartDo.setGoodsnum(cartDo.getGoodsnum()+1);
			cartDo.setTotalsum(cartDo.getTotalsum().add(cartDo.getGoods().getPrice()));
		}else{
			if(cartDo.getGoodsnum()==1){
				CustomercartDAO.deleteById(cart.getId());
				return true;
			}
			cartDo.setGoodsnum(cartDo.getGoodsnum()-1);
			cartDo.setTotalsum(cartDo.getTotalsum().subtract((cartDo.getGoods().getPrice())));
		}
		return true;
	}

}
