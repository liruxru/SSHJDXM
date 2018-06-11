package com.oracle.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

public class Search {

	private String cxloginname;
	private String cxusername;
	private Integer vipId;
	private String cxtime;
	private Timestamp cxminzhuce;
	private Timestamp cxmaxzhuce;
	private BigDecimal cxminJifen;
	private BigDecimal cxmaxJifen;
	private String cxshengxu;
	private String cxjiangxu;
	public String getCxloginname() {
		return cxloginname;
	}
	public void setCxloginname(String cxloginname) {
		this.cxloginname = cxloginname;
	}
	public String getCxusername() {
		return cxusername;
	}
	public void setCxusername(String cxusername) {
		this.cxusername = cxusername;
	}
	public Integer getVipId() {
		return vipId;
	}
	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}
	public String getCxtime() {
		return cxtime;
	}
	public void setCxtime(String cxtime) {
		this.cxtime = cxtime;
	}
	public Timestamp getCxminzhuce() {
		return cxminzhuce;
	}
	public void setCxminzhuce(Timestamp cxminzhuce) {
		this.cxminzhuce = cxminzhuce;
	}
	public Timestamp getCxmaxzhuce() {
		return cxmaxzhuce;
	}
	public void setCxmaxzhuce(Timestamp cxmaxzhuce) {
		this.cxmaxzhuce = cxmaxzhuce;
	}
	public BigDecimal getCxminJifen() {
		return cxminJifen;
	}
	public void setCxminJifen(BigDecimal cxminJifen) {
		this.cxminJifen = cxminJifen;
	}
	public BigDecimal getCxmaxJifen() {
		return cxmaxJifen;
	}
	public void setCxmaxJifen(BigDecimal cxmaxJifen) {
		this.cxmaxJifen = cxmaxJifen;
	}
	public String getCxshengxu() {
		return cxshengxu;
	}
	public void setCxshengxu(String cxshengxu) {
		this.cxshengxu = cxshengxu;
	}
	public String getCxjiangxu() {
		return cxjiangxu;
	}
	public void setCxjiangxu(String cxjiangxu) {
		this.cxjiangxu = cxjiangxu;
	}
	public Search(String cxloginname, String cxusername, Integer vipId,
			String cxtime, Timestamp cxminzhuce, Timestamp cxmaxzhuce,
			BigDecimal cxminJifen, BigDecimal cxmaxJifen, String cxshengxu,
			String cxjiangxu) {
		super();
		this.cxloginname = cxloginname;
		this.cxusername = cxusername;
		this.vipId = vipId;
		this.cxtime = cxtime;
		this.cxminzhuce = cxminzhuce;
		this.cxmaxzhuce = cxmaxzhuce;
		this.cxminJifen = cxminJifen;
		this.cxmaxJifen = cxmaxJifen;
		this.cxshengxu = cxshengxu;
		this.cxjiangxu = cxjiangxu;
	}
	public Search() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Search [cxloginname=" + cxloginname + ", cxusername="
				+ cxusername + ", vipId=" + vipId + ", cxtime=" + cxtime
				+ ", cxminzhuce=" + cxminzhuce + ", cxmaxzhuce=" + cxmaxzhuce
				+ ", cxminJifen=" + cxminJifen + ", cxmaxJifen=" + cxmaxJifen
				+ ", cxshengxu=" + cxshengxu + ", cxjiangxu=" + cxjiangxu + "]";
	}
	
	
	
}
