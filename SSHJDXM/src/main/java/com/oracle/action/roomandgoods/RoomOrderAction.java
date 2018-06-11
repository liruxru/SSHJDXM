package com.oracle.action.roomandgoods;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.comm.SH;
import com.oracle.entity.Customerorders;
import com.oracle.entity.Rooms;
import com.oracle.entity.Users;
import com.oracle.service.OrderService;


@Namespace("/room/order")
@ParentPackage("json-default")
@Controller
public class RoomOrderAction extends ActionSupport {
	private Integer[] cartId;

	public Integer[] getCartId() {
		return cartId;
	}

	public void setCartId(Integer[] cartId) {
		this.cartId = cartId;
	}
	private Integer orderId;	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	@Autowired
	private OrderService  orderService;	
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 刘洋通过房间id创建订单
	 * 创建订单
	 */
	@Action(value="creatOrder",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/room/order","actionName","listGoodsOrder"})
	})
	public String creatOrder(){
		Rooms room= (Rooms) ActionContext.getContext().getSession().get("room");
		boolean bln=orderService.creatOrder(cartId,room);
		if(bln){
			return SUCCESS;
		}
		return NONE;		
		
	}
	/**
	 * 浏览订单
	 */
	@Action(value="listGoodsOrder",results={
			@Result(name=SUCCESS,location="/page/room/roomOrder.jsp")
	})
	public String listGoodsOrder(){
		Rooms room= (Rooms) ActionContext.getContext().getSession().get("room");
		List<Customerorders>  orderList=orderService.listGoodsOrderByRoom(room);
		ActionContext.getContext().put("orderList",orderList);
		return SUCCESS;		
		
	}
	/***************************************************************************************/
	/**
	 * 刘洋通过房间id创建订单
	 * 创建订单
	 */
	@Action(value="creatOrderByRoom",results={
			@Result(name=SUCCESS,type="redirectAction",params={"namespace","/room/order","actionName","listGoodsOrder"})
	})
	public String creatOrderByRoom(){
		Rooms room= (Rooms) ActionContext.getContext().getSession().get("room");
		boolean bln=orderService.creatOrder(room);
		if(bln){
			return SUCCESS;
		}
		return NONE;		
		
	}
	/**
	 * 修改订单状态
	 * 创建订单
	 */
	@Action(value="alertOrder",results={
			@Result(name=SUCCESS,type="json",params={"root","0"})
	})
	public String alertOrder(){
	
		boolean bln=orderService.alertOrder(orderId);
		if(bln){
			return SUCCESS;
		}
		return NONE;		
		
	}
	
	
}
