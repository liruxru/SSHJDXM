package com.oracle.action.admin;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.oracle.bean.Address;
import com.oracle.bean.Page;
import com.oracle.bean.Search;
import com.oracle.comm.SH;
import com.oracle.comm.SSH;
import com.oracle.entity.Users;
import com.oracle.service.UserService;
import com.oracle.service.UserVipService;
import com.oracle.service.shqService;

@ParentPackage("json-default")
@Namespace("/admin/huiyuan")
@Controller
public class HuiYuanAction {
	
	@Autowired
	private UserService userService;
	@Autowired
	private shqService shqService;
	@Autowired
	private UserVipService userVipService;
	
	private Search search;
	private Integer currentPage;
	private Address address;
	private Users user;
	private JSONObject JestText;
	private String loginName;
	private Integer ch[];
	private String strcxminJifen;
	private String strcxmaxJifen;
	
	
	public String getStrcxminJifen() {
		return strcxminJifen;
	}
	public void setStrcxminJifen(String strcxminJifen) {
		this.strcxminJifen = strcxminJifen;
	}
	public String getStrcxmaxJifen() {
		return strcxmaxJifen;
	}
	public void setStrcxmaxJifen(String strcxmaxJifen) {
		this.strcxmaxJifen = strcxmaxJifen;
	}
	public Search getSearch() {
		return search;
	}
	public void setSearch(Search search) {
		this.search = search;
	}
	public UserVipService getUserVipService() {
		return userVipService;
	}
	public void setUserVipService(UserVipService userVipService) {
		this.userVipService = userVipService;
	}
	public Integer[] getCh() {
		return ch;
	}
	public void setCh(Integer[] ch) {
		this.ch = ch;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public JSONObject getJestText() {
		return JestText;
	}
	public void setJestText(JSONObject jestText) {
		JestText = jestText;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public shqService getShqService() {
		return shqService;
	}
	public void setShqService(shqService shqService) {
		this.shqService = shqService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Action(value="userlist",results={
			@Result(name="success",location="/page/admin/huiyuan/list.jsp")
	})
	public String userList() throws UnsupportedEncodingException{
		HttpServletRequest request=ServletActionContext.getRequest();
		Integer sessionCurrentPage=(Integer) ActionContext.getContext().getSession().get(SH.SESSION_PAGE_KEY);
		if (currentPage==null) {
			currentPage=1;
		}
		if (sessionCurrentPage!=null) {
			currentPage=sessionCurrentPage;
			ActionContext.getContext().getSession().remove(SH.SESSION_PAGE_KEY);
		}
		
		if (search!=null) {
			if (search.getCxloginname()!=null && !"".equals(search.getCxloginname())) {
				if (SSH.TIJIAOFANGSHI.equalsIgnoreCase(request.getMethod())) {
					search.setCxloginname(new String(search.getCxloginname().getBytes("iso-8859-1"),"utf-8"));
				}
			}
			if (search.getCxusername()!=null && !"".equals(search.getCxusername())) {
				if (SSH.TIJIAOFANGSHI.equalsIgnoreCase(request.getMethod())) {
					search.setCxusername(new String(search.getCxusername().getBytes("iso-8859-1"), "utf-8"));
				}
			}
			if (search.getCxshengxu()!=null && !"".equals(search.getCxshengxu())) {
				if (SSH.TIJIAOFANGSHI.equalsIgnoreCase(request.getMethod())) {
					search.setCxshengxu(new String(search.getCxshengxu().getBytes("iso-8859-1"), "utf-8"));
				}
			}
			if (search.getCxjiangxu()!=null && !"".equals(search.getCxjiangxu())) {
				if (SSH.TIJIAOFANGSHI.equalsIgnoreCase(request.getMethod())) {
					search.setCxjiangxu(new String(search.getCxjiangxu().getBytes("iso-8859-1"), "utf-8"));
				}
			}
			
		}
		
		if (strcxminJifen!=null && !"".equals(strcxminJifen)) {
			BigDecimal minJifen=new  BigDecimal(strcxminJifen);
			search.setCxminJifen(minJifen);
		}
		if (strcxmaxJifen!=null && !"".equals(strcxmaxJifen)) {
			BigDecimal manJifen=new  BigDecimal(strcxmaxJifen);
			search.setCxmaxJifen(manJifen);
		}
		
		Integer pageSize=9;
		Page page=userService.finAll(currentPage,pageSize,search);
		ActionContext.getContext().put("cx", search);
		ActionContext.getContext().put("strcxminJifen", strcxminJifen);
		ActionContext.getContext().put("strcxmaxJifen", strcxmaxJifen);
		ActionContext.getContext().put(SH.SESSION_PAGE_KEY, page);
		ActionContext.getContext().put("userList",page.getList() );
		ActionContext.getContext().put("vip", userVipService.findAll());
		return "success";
	}
	
	@Action(value="useredit",results={
			@Result(name="success",location="/page/admin/huiyuan/add.jsp")
	})
	public String userEdit(){
		Users userEr=(Users) ActionContext.getContext().get("userDo");
		if (userEr!=null) {
			ActionContext.getContext().put("userDo", user);
		}
		if (user!=null) {
			ActionContext.getContext().getSession().put(SH.SESSION_PAGE_KEY, currentPage);
			Users u=userService.findUserById(user.getId());
			String addr=u.getAddress();
			System.out.println("编辑里面的地址："+addr);
			ActionContext.getContext().put("userDo",u );
			
		}
		ActionContext.getContext().put("listProvince", shqService.finAllProvince());
		return "success";
	}
	//添加修改会员
	@Action(value="useradd",results={
			@Result(name="success",type="redirectAction",location="userlist"),
			@Result(name="error",type="chain",location="useredit")
	})
	public String userAdd(){
		if (userService.addUsers(user,address)) {
			return "success";
		}else {
			ActionContext.getContext().put("userDo", user);
			return "error";
		}
		
	}
	//验证会员名称
	@Action(value="checkuser",results={
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String checkUser(){
		if (userService.checkUserName(loginName)) {
			return "success";
		}else {
			return "error";
		}
	}
	//删除会员
	@Action(value="userdelete",results={
			@Result(name="allsucc",type="redirectAction",location="userlist"),
			@Result(name="success",type="json",params={"root","0"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String userDelete(){
		
		if (ch!=null&&ch.length>1) {
			for (int i = 0; i < ch.length; i++) {
				Users u=userService.findUserById(ch[i]);
				userService.delete(u.getId());
			}
			return "allsucc";
		}else {
			userService.delete(user.getId());
			return "success";
		}
		
	}
	

}
