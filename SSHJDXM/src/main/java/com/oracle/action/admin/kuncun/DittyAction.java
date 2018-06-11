package com.oracle.action.admin.kuncun;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.comm.SH;
import com.oracle.entity.Ditty;
import com.oracle.service.DittyService;
import com.oracle.service.EntrepotService;
import com.oracle.service.ProviderService;


@Namespace("/admin/kucun")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class DittyAction {
	@Resource(name="dittyService")
	private DittyService ds;
	@Resource(name="providerService")
	private ProviderService ps;
	@Resource(name="entrepotService")
	private EntrepotService es;
	private Ditty ditty;
	private Integer ch[];

	public DittyService getDs() {
		return ds;
	}
	public void setDs(DittyService ds) {
		this.ds = ds;
	}	
	public ProviderService getPs() {
		return ps;
	}
	public void setPs(ProviderService ps) {
		this.ps = ps;
	}	
	public EntrepotService getEs() {
		return es;
	}
	public void setEs(EntrepotService es) {
		this.es = es;
	}
	public Ditty getDitty() {
		return ditty;
	}
	public void setDitty(Ditty ditty) {
		this.ditty = ditty;
	}	
	public Integer[] getCh() {
		return ch;
	}
	public void setCh(Integer[] ch) {
		this.ch = ch;
	}
	@Action(value="dittylist",results={
			@Result(name="success",location="/page/admin/kucun/ditty/list.jsp")
	})
	public String list(){
		ActionContext.getContext().put(SH.SESSION_DITTYLIST_KEY, ds.list());
		return "success";
		
	}
	
	@Action(value="dittyedit",results={
			@Result(name="success",location="/page/admin/kucun/ditty/add.jsp")
	})
	public String edit(){
		if(ditty!=null){
			Ditty d = ds.findById(ditty.getId());
			ActionContext.getContext().put(SH.SESSION_DITTYS_KEY,d);
		}
		ActionContext.getContext().put(SH.SESSION_PROVIDERLIST_KEY,ps.list());
		ActionContext.getContext().put(SH.SESSION_EDITENTREPOTLIST_KEY, es.list());
		return "success";
		
	}
	
	@Action(value="dittyadd",results={
			@Result(name="success",type="redirectAction",location="dittylist"),
			@Result(name="error",type="chain",location="dittyedit")
	})
	public String add(){
		if(ds.save(ditty)){
			return "success";
		}else{
			ActionContext.getContext().put(SH.SESSION_DITTY_KEY, ditty);
			return "error";
		}
		
	}
	
	@Action(value="dittydelete",results={
			@Result(name="suc",type="redirectAction",location="dittylist"),
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String delete(){
		if(ch!=null&&ch.length>1){
		   for (int i = 0; i < ch.length; i++) {
			Ditty d = ds.findById(ch[i]);
			ds.delete(d);
		   }
			return "suc";
		}
		if(ds.delete(ditty)){
			return "success";
		}else{
			return "error";
		}
		
	}

}
