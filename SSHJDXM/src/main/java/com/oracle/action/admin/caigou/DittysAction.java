package com.oracle.action.admin.caigou;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.comm.SH;
import com.oracle.entity.Ditty;
import com.oracle.entity.Entrepot;
import com.oracle.entity.Goods;
import com.oracle.entity.Provider;
import com.oracle.service.DittyService;
import com.oracle.service.EntrepotService;
import com.oracle.service.GoodsService;

@Namespace("/admin/caigou")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class DittysAction { 
	
	@Resource(name="entrepotService")
	private EntrepotService es;
	@Resource(name="dittyService")
	private DittyService    ds;
	@Resource(name="goodsService")
	private GoodsService    gs;
	private Integer entrepotId;
	private JSONArray jsonArray;
	
	public EntrepotService getEs() {
		return es;
	}
	public void setEs(EntrepotService es) {
		this.es = es;
	}
	public DittyService getDs() {
		return ds;
	}
	public void setDs(DittyService ds) {
		this.ds = ds;
	}
	public Integer getEntrepotId() {
		return entrepotId;
	}
	public void setEntrepotId(Integer entrepotId) {
		this.entrepotId = entrepotId;
	}	
	public GoodsService getGs() {
		return gs;
	}
	public void setGs(GoodsService gs) {
		this.gs = gs;
	}
	public JSONArray getJsonArray() {
		return jsonArray;
	}
	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}
	@Action(value="dittyedit",results={
			@Result(name="suceess",location="/page/admin/kucun/caigou/list.jsp")
	})
	public String showSn(){
		//获取库存少于50的；
		List<Ditty> ls = ds.getDitty();
		List<Goods> list  = gs.getGood();
		ActionContext.getContext().put(SH.SESSION_DITTYLIST_KEY, ls);
		ActionContext.getContext().put(SH.SESSION_GOODSLIST_KEY, list);
		ActionContext.getContext().put(SH.SESSION_EDITENTREPOTLIST_KEY, es.list());
		return "suceess";
	}
	@Action(value="dittyupdate",results={
			@Result(name="success",type="json",params={"root","jsonArray"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String show(){
		List<Ditty> ls = ds.getDittyByEntrepotId(entrepotId);
		/*for (Ditty dittys : ls) {
			System.out.println("原来的"+dittys.getFullname());
		}*/
		List<Ditty> lss=new ArrayList<Ditty>();	
			for (Ditty d : ls) {
				Ditty ditty=new Ditty();
				ditty.setName(d.getName());
				ditty.setFullname(d.getFullname());
				ditty.setId(d.getId());
				ditty.setNum(d.getNum());
				ditty.setPrice(d.getPrice());
				Provider p=new Provider();
				p.setId(d.getProvider().getId());
				p.setProvidename(d.getProvider().getProvidename());
				ditty.setProvider(p);
				lss.add(ditty);
			}
			/*for (Ditty ditt : lss) {
				System.out.println("之后的"+ditt.getFullname());
			}
	    System.out.println("哈哈哈哈哈"+lss);*/
		List<Goods> list  = gs.getGoodsByEntrepotId(entrepotId);
		List<Goods> list1 = new ArrayList<Goods>();
		//System.out.println("1嘿嘿嘿嘿嘿"+list);
		for (Goods g : list) {
			Goods goods = new Goods();
			goods.setId(g.getId());
			goods.setName(g.getName());
			goods.setFullname(g.getFullname());
			goods.setNum(g.getNum());
			goods.setPrice(g.getPrice());
			Provider p=new Provider();
			p.setId(g.getProvider().getId());
			p.setProvidename(g.getProvider().getProvidename());
			goods.setProvider(p);
			list1.add(goods);
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put(SH.SESSION_DITTYLIST_KEY, lss);
		map.put(SH.SESSION_GOODSLIST_KEY, list1);
		JSONArray  jsonText = JSONArray.fromObject(map);
		jsonArray=jsonText;
		/*System.out.println("2嘿嘿"+list1);
		JSONArray  json2 = JSONArray.fromObject(list1);
		System.out.println("3嘿嘿"+json2.toString());
		jsonArray=json2;*/		
		/*JSONArray  json1 = JSONArray.fromObject(lss);
		System.out.println("哈"+json1.toString());
		jsonArray=json1;*/	
		return "success";
	}
	
	@Action(value="dittyadd",results={
			@Result(name="suceess",location="/page/admin/kucun/caigou/add.jsp")
	})
	public String add(){
		ActionContext.getContext().put(SH.SESSION_DITTYLIST_KEY, ds.list());
		return "suceess";
	}
	
	

}
