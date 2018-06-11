package com.oracle.bean;

import org.springframework.stereotype.Component;

@Component
public class Address {

	private String sheng;
	private String shi;
	private String qu;
	public String getSheng() {
		return sheng;
	}
	public void setSheng(String sheng) {
		this.sheng = sheng;
	}
	public String getShi() {
		return shi;
	}
	public void setShi(String shi) {
		this.shi = shi;
	}
	public String getQu() {
		return qu;
	}
	public void setQu(String qu) {
		this.qu = qu;
	}
	@Override
	public String toString() {
		return "Address [sheng=" + sheng + ", shi=" + shi + ", qu=" + qu + "]";
	}
	public Address(String sheng, String shi, String qu) {
		super();
		this.sheng = sheng;
		this.shi = shi;
		this.qu = qu;
	}
	public Address() {
		super();
	}
}
