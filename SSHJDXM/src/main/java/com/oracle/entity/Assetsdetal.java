package com.oracle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Assetsdetal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assetsdetal", catalog = "sshxm")
public class Assetsdetal implements java.io.Serializable {

	// Fields

	private BigDecimal id;
	private Staffs staffs;
	private String sn;
	private BigDecimal jinzhang;
	private BigDecimal chuzhang;
	private BigDecimal sum;
	private Timestamp creratedate;

	// Constructors

	/** default constructor */
	public Assetsdetal() {
	}

	/** full constructor */
	public Assetsdetal(Staffs staffs, String sn, BigDecimal jinzhang,
			BigDecimal chuzhang, BigDecimal sum, Timestamp creratedate) {
		this.staffs = staffs;
		this.sn = sn;
		this.jinzhang = jinzhang;
		this.chuzhang = chuzhang;
		this.sum = sum;
		this.creratedate = creratedate;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffs")
	public Staffs getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}

	@Column(name = "sn")
	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "jinzhang", precision = 10)
	public BigDecimal getJinzhang() {
		return this.jinzhang;
	}

	public void setJinzhang(BigDecimal jinzhang) {
		this.jinzhang = jinzhang;
	}

	@Column(name = "chuzhang", precision = 10)
	public BigDecimal getChuzhang() {
		return this.chuzhang;
	}

	public void setChuzhang(BigDecimal chuzhang) {
		this.chuzhang = chuzhang;
	}

	@Column(name = "sum")
	public BigDecimal getSum() {
		return this.sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	@Column(name = "creratedate", length = 19)
	public Timestamp getCreratedate() {
		return this.creratedate;
	}

	public void setCreratedate(Timestamp creratedate) {
		this.creratedate = creratedate;
	}

}