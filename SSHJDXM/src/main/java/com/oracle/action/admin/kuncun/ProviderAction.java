package com.oracle.action.admin.kuncun;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;





import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.bean.Times;
import com.oracle.comm.SH;
import com.oracle.entity.Entrepot;
import com.oracle.entity.Provider;
import com.oracle.service.ProviderService;

@Namespace("/admin/provider")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class ProviderAction {	
	@Resource(name="providerService")
	private ProviderService ps;
	private Provider provider;

	public ProviderService getPs() {
		return ps;
	}
	public void setPs(ProviderService ps) {
		this.ps = ps;
	}	
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	
	@Action(value="providerList",results={
			@Result(name="success",location="/page/admin/kucun/provider/list.jsp")
	})
	public String list() throws Exception{
		List<Times> ls = new ArrayList<Times>();		
		for (Provider  P: ps.list()) {
			Times t = new Times();
			Date date = new Date();
			Long l =null;
			if(P.getCreatedate()==null){
				l=0l;
				BeanUtils.copyProperties(P, t);
				t.setP(l.toString());
				ls.add(t);
				continue;
				
			}			
//			System.out.println("创建时间"+P.getCreatedate().getTime());
//			System.out.println(date.getTime());
			l = (date.getTime()-P.getCreatedate().getTime())/1000/60/60/24;			
			double a= (date.getTime()-P.getCreatedate().getTime())/1000/60/60/24;
			double b= (date.getTime()-P.getCreatedate().getTime())/100/60/60/24%10;	
			double c= a-b;
			if(c==0){
				l=l;
			}
			else{
				l=l+1;
			}
			BeanUtils.copyProperties(P, t);
			t.setP(l.toString());
			ls.add(t);
			
		}
		ActionContext.getContext().put(SH.SESSION_PROVIDERLIST_KEY, ls);
		return "success";
	}
	
	@Action(value="providerEdit",results={
			@Result(name="success",location="/page/admin/kucun/provider/add.jsp")
	})
	public String edit(){
		if(provider!=null){
			Provider p = ps.findById(provider.getId());
			ActionContext.getContext().put(SH.SESSION_PROVIDERS_KEY, p);
		}
		return "success";
	}
	
	@Action(value="providerAdd",results={
			@Result(name="success",type="redirectAction",location="providerList"),
			@Result(name="error",type="chain",location="providerEdit")
	})
	public String add(){
		if(ps.save(provider)){
			return "success";
		}else{
			ActionContext.getContext().put(SH.SESSION_PROVIDER_KEY, provider);
			return "error";
		}
	}
	
	@Action(value="providerDelete",results={
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String delete(){
		if(ps.delete(provider)){
			return "success";
		}else{
			return "error";
		}
	}
		
	

}
