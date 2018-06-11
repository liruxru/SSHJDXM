package com.oracle.action.admin.yuangong;

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
import com.oracle.bean.Page;
import com.oracle.bean.StaffTime;
import com.oracle.bean.Times;
import com.oracle.comm.SH;
import com.oracle.entity.Ditty;
import com.oracle.entity.Goods;
import com.oracle.entity.Provider;
import com.oracle.entity.Staffs;
import com.oracle.service.StaffsService;

@Namespace("/admin/staffs")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class StaffisAction {
	@Resource(name="StaffsService")
	private StaffsService ss;
	private Integer currentPage;
	private Staffs  staffs;
	private Integer ch[];

	public StaffsService getSs() {
		return ss;
	}
	public void setSs(StaffsService ss) {
		this.ss = ss;
	}	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}	
	public Staffs getStaffs() {
		return staffs;
	}
	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}
	public Integer[] getCh() {
		return ch;
	}
	public void setCh(Integer[] ch) {
		this.ch = ch;
	}
	@Action(value="staffslist",results={
			@Result(name="success",location="/page/admin/yuangong/list.jsp")
	})
	public String list(){
		Integer curr = (Integer) ActionContext.getContext().getSession().get("currentPage");
		if(currentPage==null){
			currentPage=1;
		}
		if(curr!=null){
			currentPage=curr;
			ActionContext.getContext().getSession().remove("currentPage");
		}
		Integer pageSize=9;
		Page page= ss.list(currentPage,pageSize);
		List<StaffTime> ls = new ArrayList<StaffTime>();
		List<Staffs> StaffsLs = page.getList();
		for (Staffs  s: StaffsLs) {
			StaffTime t = new StaffTime();
			Date date = new Date();
			Long l =null;
			if(s.getCreatedate()==null){
				l=0l;
				BeanUtils.copyProperties(s, t);
				t.setTimes(l.toString());
				ls.add(t);
				continue;				
			}	
//			System.out.println("创建时间"+P.getCreatedate().getTime());
//			System.out.println(date.getTime());
			l = (date.getTime()-s.getCreatedate().getTime())/1000/60/60/24/365;	
			
			double a= (date.getTime()-s.getCreatedate().getTime())/1000/60/60/24/365;
			double b= (date.getTime()-s.getCreatedate().getTime())/100/60/60/24/365%10;	
			double c= a-b;
			if(c==0){
				l=l;
			}
			else{
				l=l+1;
			}
			BeanUtils.copyProperties(s, t);
			t.setTimes(l.toString());
			ls.add(t);
		}		
		ActionContext.getContext().put(SH.SESSION_PAGE_KEY, page);
		ActionContext.getContext().put(SH.SESSION_LISTSTAFF_KEY,ls);
		return "success";		
	}
	

	@Action(value="staffsedit",results={
			@Result(name="success",location="/page/admin/yuangong/add.jsp")
	})
	public String edit(){
		if(staffs!=null){
			Staffs sta = ss.findById(staffs.getId());
			ActionContext.getContext().getSession().put(SH.SESSION_CURRENTPAGE_KEY, currentPage);
			ActionContext.getContext().put(SH.SESSION_STAFF_KEY, sta);
		}
		return "success";
		
	}
	
	@Action(value="staffsadd",results={
			@Result(name="success",type="redirectAction",location="staffslist")
	})
	public String add(){
		if(ss.save(staffs)){
			return "success";
		}else{
			ActionContext.getContext().put(SH.SESSION_STAFFS_KEY, staffs);
			return "error";
		}
		
	}
	
	@Action(value="staffsdelete",results={
			@Result(name="suc",type="redirectAction",location="staffslist"),
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String delete(){
		if(ch!=null&&ch.length>1){
			   for (int i = 0; i < ch.length; i++) {
				   Staffs sta = ss.findById(ch[i]);
				   ss.delete(sta);
			   }
				return "suc";
		}
		if(ss.delete(staffs)){
			return "success";
		}else{
			return "error";
		}
		
	}
	
	

}
