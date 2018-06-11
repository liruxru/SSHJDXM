package com.oracle.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usersvip entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usersvip", catalog = "sshxm")
public class Usersvip implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long jifen;
	private Integer level;
	private String vipname;
	private Double roomdiscount;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Usersvip() {
	}

	/** full constructor */
	public Usersvip(Long jifen, Integer level, String vipname,
			Double roomdiscount) {
		this.jifen = jifen;
		this.level = level;
		this.vipname = vipname;
		this.roomdiscount = roomdiscount;
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

	@Column(name = "jifen", precision = 10, scale = 0)
	public Long getJifen() {
		return this.jifen;
	}

	public void setJifen(Long jifen) {
		this.jifen = jifen;
	}

	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "vipname")
	public String getVipname() {
		return this.vipname;
	}

	public void setVipname(String vipname) {
		this.vipname = vipname;
	}

	@Column(name = "roomdiscount", precision = 10)
	public Double getRoomdiscount() {
		return this.roomdiscount;
	}

	public void setRoomdiscount(Double roomdiscount) {
		this.roomdiscount = roomdiscount;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersvip")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}
}