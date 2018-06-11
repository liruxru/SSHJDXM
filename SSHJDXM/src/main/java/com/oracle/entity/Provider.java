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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Provider entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "provider", catalog = "sshxm")
public class Provider implements java.io.Serializable {

	// Fields

	private Integer id;
	private String providename;
	private String providephone;
	private String provideaddress;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Ditty> ditties = new HashSet<Ditty>(0);
	private Set<Goods> goodses = new HashSet<Goods>(0);

	// Constructors

	/** default constructor */
	public Provider() {
	}

	/** full constructor */
	public Provider(String providename, String providephone,
			String provideaddress, Timestamp createdate, Timestamp moditfydate,
			Set<Ditty> ditties, Set<Goods> goodses) {
		this.providename = providename;
		this.providephone = providephone;
		this.provideaddress = provideaddress;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
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

	@Column(name = "providename")
	public String getProvidename() {
		return this.providename;
	}

	public void setProvidename(String providename) {
		this.providename = providename;
	}

	@Column(name = "providephone")
	public String getProvidephone() {
		return this.providephone;
	}

	public void setProvidephone(String providephone) {
		this.providephone = providephone;
	}

	@Column(name = "provideaddress")
	public String getProvideaddress() {
		return this.provideaddress;
	}

	public void setProvideaddress(String provideaddress) {
		this.provideaddress = provideaddress;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "provider")
	public Set<Ditty> getDitties() {
		return this.ditties;
	}

	public void setDitties(Set<Ditty> ditties) {
		this.ditties = ditties;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "provider")
	public Set<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

	

}