package com.oracle.action.admin.kuncun;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.comm.SH;
import com.oracle.entity.Entrepot;
import com.oracle.service.EntrepotService;
import com.oracle.service.StaffsService;

@Namespace("/admin/entrepot")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class EntrepotAction{
	
	@Resource(name="entrepotService")
	private EntrepotService es;
	@Resource(name="StaffsService")
	private StaffsService ss;
	private Entrepot entrepot;
	private JSONObject jsonText;

	public EntrepotService getEs() {
		return es;
	}
	public void setEs(EntrepotService es) {
		this.es = es;
	}
	public StaffsService getSs() {
		return ss;
	}
	public void setSs(StaffsService ss) {
		this.ss = ss;
	}
	public Entrepot getEntrepot() {
		return entrepot;
	}
	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}	
	public JSONObject getJsonText() {
		return jsonText;
	}
	public void setJsonText(JSONObject jsonText) {
		this.jsonText = jsonText;
	}
	@Action(value="entrepotList",results={
			@Result(name="suceess",location="/page/admin/kucun/entrepot/list.jsp")
	})
	public String show(){
		ActionContext.getContext().put("listEntrepot", es.list());
		return "suceess";
		
	}
	
	@Action(value="entrepotEdit",results={
			@Result(name="suceess",location="/page/admin/kucun/entrepot/add.jsp")
	})
	public String edit(){
		if(entrepot!=null){
			Entrepot en = es.findById(entrepot.getId());
			ActionContext.getContext().put(SH.SESSION_EDITENTREPOT_KEY, en);
		}
		ActionContext.getContext().put(SH.SESSION_LISTSTAFF_KEY, ss.findAll());
		return "suceess";
		
	}
	
	@Action(value="entrepotAdd",results={
			@Result(name="success",type="redirectAction",location="entrepotList"),
			@Result(name="error",type="chain",location="entrepotEdit")
	})
	public String add(){
		if(es.save(entrepot)){
			return "success";
		}else{
			ActionContext.getContext().put(SH.SESSION_ENTREPOT_KEY, entrepot);
			return "error";
		}
		
	}
	
	@Action(value="entrepotDelete",results={
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String delete(){
		if(es.delete(entrepot)){
			return "success";
		}else{
			return "error";
		}
		
	}
	

}
