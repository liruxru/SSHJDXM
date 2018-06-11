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
 * Sexs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sexs", catalog = "sshxm")
public class Sexs implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sex;
	 private Set<Usersputong> usersputongs = new HashSet<Usersputong>(0);

	// Constructors

	/** default constructor */
	public Sexs() {
	}

	/** full constructor */
	public Sexs(String sex) {
		this.sex = sex;
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

	@Column(name = "sex")
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sex")
	public Set<Usersputong> getUsersputongs() {
		return usersputongs;
	}

	public void setUsersputongs(Set<Usersputong> usersputongs) {
		this.usersputongs = usersputongs;
	}
	
	

}