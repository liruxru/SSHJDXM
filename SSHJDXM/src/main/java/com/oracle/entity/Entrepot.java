package com.oracle.entity;

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
 * Entrepot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "entrepot", catalog = "sshxm")
public class Entrepot implements java.io.Serializable {

	// Fields

	private Integer id;
	private Staffs staffs;
	private String sn;
	private String phone;
	private String address;
	private Set<Caigou> caigous = new HashSet<Caigou>(0);
	private Set<Ditty> ditties = new HashSet<Ditty>(0);
	private Set<Goods> goodses = new HashSet<Goods>(0);

	// Constructors

	/** default constructor */
	public Entrepot() {
	}

	/** full constructor */
	public Entrepot(Staffs staffs, String sn, String phone, String address,
			Set<Caigou> caigous, Set<Ditty> ditties, Set<Goods> goodses) {
		this.staffs = staffs;
		this.sn = sn;
		this.phone = phone;
		this.address = address;
		this.caigous = caigous;
		this.ditties = ditties;
		this.goodses = goodses;
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
	@JoinColumn(name = "staff")
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

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entrepot")
	public Set<Caigou> getCaigous() {
		return this.caigous;
	}

	public void setCaigous(Set<Caigou> caigous) {
		this.caigous = caigous;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entrepot")
	public Set<Ditty> getDitties() {
		return this.ditties;
	}

	public void setDitties(Set<Ditty> ditties) {
		this.ditties = ditties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "entrepot")
	public Set<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

}