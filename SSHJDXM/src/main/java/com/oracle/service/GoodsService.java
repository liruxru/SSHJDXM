package com.oracle.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.bean.Page;
import com.oracle.dao.GoodsDAO;
import com.oracle.dao.GoodsimgDAO;
import com.oracle.entity.Entrepot;
import com.oracle.entity.Goods;
import com.oracle.entity.Goodsimg;

@Service("goodsService")
public class GoodsService {
	@Resource(name="GoodsDAO")
	private  GoodsDAO goodsDAO;
	@Resource(name="GoodsimgDAO")
	private GoodsimgDAO goodsimgDAO;

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}
	public void setGoodsDAO(GoodsDAO goodsDAO) {
		this.goodsDAO = goodsDAO;
	}	
	public GoodsimgDAO getGoodsimgDAO() {
		return goodsimgDAO;
	}
	public void setGoodsimgDAO(GoodsimgDAO goodsimgDAO) {
		this.goodsimgDAO = goodsimgDAO;
	}
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Page list(Integer currentPage, Integer pageSize) {
		//先查询总记录数
		Integer count = goodsDAO.countId();
		//总页数
		Integer totalPage=Page.getTotalPage(count, pageSize);
		//当前记录数
		Integer offset = Page.getOffset(currentPage, pageSize);
		//每次取得长度
		Integer length = Page.length(pageSize);
		List<Goods> list =goodsDAO.findAll(offset,length);
		Page page = new Page(count, totalPage, currentPage, list);
		return page;
	}
	
//	/**
//	 * 浏览全部商品
//	 * @return
//	 */
//	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
//	public List<Goods> findAll() {		
//		return goodsDAO.findAll(1);
//	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean save(Goods goods, String newImgcover, List<String> ls) {
//		//当id为空时，是添加商品操作
//		if(goods.getId()==null){
			goods.setImgcover(newImgcover);
			goods.setStatues(0);
			goods.setCreatedate(new Timestamp(new Date().getTime()));
			Goods g=goodsDAO.merge(goods);
			Integer id=g.getId();
			for (int i = 0; i < ls.size(); i++) {
				Goodsimg img = new Goodsimg(g, ls.get(i), new Timestamp(new Date().getTime()) , null);
				goodsimgDAO.attachDirty(img);
			}
			return true;			
//		}
//		else{
//			Goods goodDo= goodsDAO.findById(goods.getId());
//			goodDo.setName(goods.getName());
//			goodDo.setFullname(goods.getFullname());
//			goodDo.setNum(goods.getNum());
//			goodDo.setSalePrice(goods.getSalePrice());
//			goodDo.setPrice(goods.getPrice());
//			goodDo.setImgcover(goods.getImgcover());
//			goodDo.setEntrepot(goods.getEntrepot());
//			goodDo.setProvider(goods.getProvider());
//			goodDo.setGoodstypes(goods.getGoodstypes());
//			goodDo.setCreatedate(goodDo.getCreatedate());
//			goodDo.setModitfydate(new Timestamp(new Date().getTime()));
//			Set<Goodsimg> set = new HashSet<Goodsimg>();
//			if(ls.size()>0){
//				for (int i = 0; i < ls.size(); i++) {					
//					Goodsimg img = new Goodsimg(goodDo, ls.get(i), null , new Timestamp(new Date().getTime()));
//					goodsimgDAO.attachDirty(img);
//					set.add(img);
//				}
//			}
//			goodDo.setGoodsimgs(set);
//			return true;
//		}
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public Goods getGoodsById(Integer id) {
		Goods goods = goodsDAO.getGoodById(id);
		return goods;
	}
	
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean edit(Goods goods, List<Goodsimg> ls) {
		Goods goodDo= goodsDAO.findById(goods.getId());
		goodDo.setName(goods.getName());
		goodDo.setFullname(goods.getFullname());
		goodDo.setNum(goods.getNum());
		goodDo.setSalePrice(goods.getSalePrice());
		goodDo.setPrice(goods.getPrice());
		goodDo.setImgcover(goods.getImgcover());
		goodDo.setEntrepot(goods.getEntrepot());
		goodDo.setStatues(goods.getStatues());
		goodDo.setProvider(goods.getProvider());
		goodDo.setGoodstypes(goods.getGoodstypes());
		goodDo.setCreatedate(goodDo.getCreatedate());
		goodDo.setModitfydate(new Timestamp(new Date().getTime()));
		if(ls.size()>0){
			List<Goodsimg> list = new ArrayList<Goodsimg>();
			for (Goodsimg goodsimg : goodDo.getGoodsimgs()) {
				list.add(goodsimg);
			}
			if(list.size()>0){
				for (int i = 0; i <ls.size(); i++) {
					goodsDAO.del(list.get(i).getId(), goodDo.getId());
				}				
			}
			Set<Goodsimg> sd = new HashSet<Goodsimg>();
			for (int i = 0; i < ls.size(); i++) {
				sd.add(ls.get(i));
			}
			goodDo.setGoodsimgs(sd);
		}
		return true;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public boolean delete(Goods goods) {
		Goods goodDo= goodsDAO.findById(goods.getId());
//		List<Goodsimg> list = new ArrayList<Goodsimg>();
//		for (Goodsimg goodsimg : goodDo.getGoodsimgs()) {
//			list.add(goodsimg);
//			//goodsimg.setGoods(goods);
//			goodDo.getGoodsimgs().remove(goodsimg);
//		}
//		for (int i = 0; i <list.size(); i++) {
//			goodsDAO.del(list.get(i).getId(), goodDo.getId());
//		}
//		System.out.println(list.size());
//		for (Goodsimg goodsimg : list){
//			goodsDAO.del(list.get(i), goods.getId());			
//		}
		goodDo.setStatues(1);
		return true;
	}
	public List<Goods> findAll() {
		return goodsDAO.findAll(1);
	}
	public boolean deleteAll(Integer[] ch) {
		for (int i = 0; i < ch.length; i++) {
			Goods goodDo= goodsDAO.findById(ch[i]);
			goodDo.setStatues(1);
		}
		return true;
	}
	public List<Goods> getGoodsByEntrepotId(Integer entrepotId) {
		List<Goods> ls = goodsDAO.getGoodsByEntrepotId(entrepotId);
		return ls;
	}
	public List<Goods> getGood() {
		List<Goods> ls = goodsDAO.getGood();
		return ls;
	}
	
	

}
