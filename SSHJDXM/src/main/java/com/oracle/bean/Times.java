package com.oracle.bean;

import java.sql.Timestamp;

public class Times {
	private Integer id;
	private String providename;
	private String providephone;
	private String provideaddress;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private String    p;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvidename() {
		return providename;
	}
	public void setProvidename(String providename) {
		this.providename = providename;
	}
	public String getProvidephone() {
		return providephone;
	}
	public void setProvidephone(String providephone) {
		this.providephone = providephone;
	}
	public String getProvideaddress() {
		return provideaddress;
	}
	public void setProvideaddress(String provideaddress) {
		this.provideaddress = provideaddress;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public Timestamp getModitfydate() {
		return moditfydate;
	}
	public void setModitfydate(Timestamp moditfydate) {
		this.moditfydate = moditfydate;
	}
	public String getP() {
		return p;
	}
	public void setP(String p) {
		this.p = p;
	}
	
	

}
