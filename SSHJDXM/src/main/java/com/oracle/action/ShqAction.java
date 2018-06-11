package com.oracle.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.oracle.entity.Area;
import com.oracle.entity.City;
import com.oracle.service.shqService;

@ParentPackage("json-default")
@Namespace("/")
public class ShqAction {

	private String shengCode;
	private String shiCode;
	private JSONArray JestText;
	@Autowired
	private shqService shqService;
	
	
	
	public String getShiCode() {
		return shiCode;
	}
	public void setShiCode(String shiCode) {
		this.shiCode = shiCode;
	}
	public JSONArray getJestText() {
		return JestText;
	}
	public void setJestText(JSONArray jestText) {
		JestText = jestText;
	}
	public shqService getShqService() {
		return shqService;
	}
	public void setShqService(shqService shqService) {
		this.shqService = shqService;
	}
	public String getShengCode() {
		return shengCode;
	}
	public void setShengCode(String shengCode) {
		this.shengCode = shengCode;
	}

	@Action(value="getListShiBysheng",results={
			@Result(name="success",type="json",params={"root","JestText"}),
			@Result(name="error",type="json",params={"root","1"})
	})
	public String getShiBysheng(){
		List<City> lsCities=shqService.getShiBySheng(shengCode);
		JSONArray jsonArray=JSONArray.fromObject(lsCities);
		JestText=jsonArray;
		return "success";
	}
	
	@Action(value="getQuByShi",results={
			@Result(name="success",type="json",params={"root","JestText"})
	})
	public String getQuByShi(){
		/*String aaString="".split(regex)*/
		List<Area> lsAreas=shqService.getQuByShi(shiCode);
		JSONArray jsonArray=JSONArray.fromObject(lsAreas);
		JestText=jsonArray;
		return "success";
	}
}
