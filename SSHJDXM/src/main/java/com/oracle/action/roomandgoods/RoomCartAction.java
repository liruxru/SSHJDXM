package com.oracle.action.roomandgoods;

import java.util.List;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.comm.SH;
import com.oracle.entity.Customercart;
import com.oracle.entity.Goods;
import com.oracle.entity.Rooms;
import com.oracle.entity.Users;
import com.oracle.service.CartService;

@Namespace("/room/cart")
@ParentPackage("json-default")
@Controller
public class RoomCartAction extends ActionSupport {
	private String tag;
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	private JSONArray jsonArray;
	
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	private Goods goods;	
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	private Customercart cart;	
	public Customercart getCart() {
		return cart;
	}
	public void setCart(Customercart cart) {
		this.cart = cart;
	}

	@Autowired
	private CartService cartService;

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	/**
	 * 添加商品到房间购物车
	 * 1.房间id,会员用户id,商品id,商品名字,购买数量，totalSum,creatdate
	 * @return
	 */
	@Action(value="addToCart",results={
			@Result(name=SUCCESS,type="json",params={"root","0"})
	})
	public String  addToCart(){
		//从session中获取Rooms,从这里还可以获取房间id no ,商品名字
		Rooms room= (Rooms) ActionContext.getContext().getSession().get("room");
		boolean bln =cartService.addToCart(goods,room);
		if(bln)
		return SUCCESS;
		return NONE;		
	}
	
	/**
	 * 刘洋通过房间查看
	 * 查看房间购物车
	 */
	@Action(value="roomCart",results={
			@Result(name=SUCCESS,type="json",params={"root","jsonArray"})
	})
	public String roomCart(){
		//通过用户id或者房间id查询购物车
		Rooms room= (Rooms) ActionContext.getContext().getSession().get("room");
		List<Customercart> cartList=cartService.roomCart(room.getId());
		jsonArray=JSONArray.fromObject(cartList);
		return SUCCESS;
		
	}
	
	/**
	 * 用户通过id移除购物车
	 */
	@Action(value="removeCart",results={
			@Result(name=SUCCESS,type="json",params={"root","0"})
	})
	public String removeCart(){
		boolean bln=cartService.remove(cart);
		
		return SUCCESS;
		
	}
	
	/**********************************************************************************/
	/**
	 * 用户修改购物车数量
	 */
	@Action(value="alertCart",results={
			@Result(name=SUCCESS,type="json",params={"root","0"}),
			@Result(name=ERROR,type="json",params={"root","1"})
	})
	public String alertCart(){
		boolean bln=cartService.alertCart(cart,tag);
		if(bln)
		return SUCCESS;
		return ERROR;
		
	}
}
