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
 * Ditty entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ditty", catalog = "sshxm")
public class Ditty implements java.io.Serializable {

	// Fields

	private Integer id;
	private Entrepot entrepot;
	private Provider provider;
	private String name;
	private String fullname;
	private Integer num;
	private Double price;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Caigoudittyitems> caigoudittyitemses = new HashSet<Caigoudittyitems>(
			0);
	private Set<Repair> repairs = new HashSet<Repair>(0);

	// Constructors

	/** default constructor */
	public Ditty() {
	}

	/** full constructor */
	public Ditty(Entrepot entrepot, Provider provider, String name,
			String fullname, Integer num, Double price, Timestamp createdate,
			Timestamp moditfydate, Set<Caigoudittyitems> caigoudittyitemses,
			Set<Repair> repairs) {
		this.entrepot = entrepot;
		this.provider = provider;
		this.name = name;
		this.fullname = fullname;
		this.num = num;
		this.price = price;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		this.caigoudittyitemses = caigoudittyitemses;
		this.repairs = repairs;
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
	@JoinColumn(name = "provider")
	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fullname")
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "price", precision = 10)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "createdate", length = 19)
	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Column(name = "moditfydate", length = 19)
	public Timestamp getModitfydate() {
		return this.moditfydate;
	}

	public void setModitfydate(Timestamp moditfydate) {
		this.moditfydate = moditfydate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ditty")
	public Set<Caigoudittyitems> getCaigoudittyitemses() {
		return this.caigoudittyitemses;
	}

	public void setCaigoudittyitemses(Set<Caigoudittyitems> caigoudittyitemses) {
		this.caigoudittyitemses = caigoudittyitemses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ditty")
	public Set<Repair> getRepairs() {
		return this.repairs;
	}

	public void setRepairs(Set<Repair> repairs) {
		this.repairs = repairs;
	}

}