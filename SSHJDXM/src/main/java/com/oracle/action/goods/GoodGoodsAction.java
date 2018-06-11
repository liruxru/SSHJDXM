package com.oracle.action.goods;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.oracle.entity.Goods;
import com.oracle.service.GoodsService;

@Namespace("/goods")
@ParentPackage("struts-default")
@Controller
public class GoodGoodsAction extends ActionSupport {
	
	@Autowired
	private GoodsService goodsService;	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	/**
	 * 浏览全部商品
	 * @return
	 */
	@Action(value="list",results={
			@Result(name=SUCCESS,location="/page/user/goods/list.jsp")
			
	})
	public String list(){
		List<Goods> goodsList=goodsService.findAll();
		ActionContext.getContext().put("goodsList", goodsList);
		return SUCCESS;
		
	}
	
	
}
