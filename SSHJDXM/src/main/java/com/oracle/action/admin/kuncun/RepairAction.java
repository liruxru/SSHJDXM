package com.oracle.action.admin.kuncun;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.comm.SH;
import com.oracle.service.RepairService;
import com.oracle.service.RoomService;

@Namespace("/admin/repair")
@ParentPackage("json-default")
@Controller
@Scope("proptype")
public class RepairAction {
	@Autowired
	private RoomService rs;
	@Resource(name="repairService")
	private RepairService res;
	
	public RoomService getRs() {
		return rs;
	}
	public void setRs(RoomService rs) {
		this.rs = rs;
	}
	public RepairService getRes() {
		return res;
	}
	public void setRes(RepairService res) {
		this.res = res;
	}

	@Action(value="repairlist",results={
			@Result(name="success",location="/page/admin/kucun/repair/list.jsp")
	})
	public String list(){
		ActionContext.getContext().put(SH.SESSION_REPAIRLIST_KEY, res.findAll());
		ActionContext.getContext().put(SH.SESSION_ROOMlIST_KEY, rs.findAllByStatues(7));
		return "success";
	}

}
