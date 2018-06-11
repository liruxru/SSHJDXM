package com.oracle.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Caigou entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "caigou", catalog = "sshxm")
public class Caigou implements java.io.Serializable {

	// Fields

	private Integer id;
	private Entrepot entrepot;
	private Staffs staffs;
	private String sn;
	private Double sum;
	private Integer status;
	private Timestamp createdate;
	private Timestamp passdate;
	private Set<Caigoudittyitems> caigoudittyitemses = new HashSet<Caigoudittyitems>(
			0);
	private Set<Caigougoodsitems> caigougoodsitemses = new HashSet<Caigougoodsitems>(
			0);

	// Constructors

	/** default constructor */
	public Caigou() {
	}

	/** full constructor */
	public Caigou(Entrepot entrepot, Staffs staffs, String sn, Double sum,
			Integer status, Timestamp createdate, Timestamp passdate,
			Set<Caigoudittyitems> caigoudittyitemses,
			Set<Caigougoodsitems> caigougoodsitemses) {
		this.entrepot = entrepot;
		this.staffs = staffs;
		this.sn = sn;
		this.sum = sum;
		this.status = status;
		this.createdate = createdate;
		this.passdate = passdate;
		this.caigoudittyitemses = caigoudittyitemses;
		this.caigougoodsitemses = caigougoodsitemses;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "entrepot")
	public Entrepot getEntrepot() {
		return this.entrepot;
	}

	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
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

	@Column(name = "sum", precision = 10)
	public Double getSum() {
		return this.sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "createdate", length = 19)
	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Column(name = "passdate", length = 19)
	public Timestamp getPassdate() {
		return this.passdate;
	}

	public void setPassdate(Timestamp passdate) {
		this.passdate = passdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "caigou")
	public Set<Caigoudittyitems> getCaigoudittyitemses() {
		return this.caigoudittyitemses;
	}

	public void setCaigoudittyitemses(Set<Caigoudittyitems> caigoudittyitemses) {
		this.caigoudittyitemses = caigoudittyitemses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "caigou")
	public Set<Caigougoodsitems> getCaigougoodsitemses() {
		return this.caigougoodsitemses;
	}

	public void setCaigougoodsitemses(Set<Caigougoodsitems> caigougoodsitemses) {
		this.caigougoodsitemses = caigougoodsitemses;
	}

}