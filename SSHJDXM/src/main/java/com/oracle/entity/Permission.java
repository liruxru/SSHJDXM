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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Permission entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "permission", catalog = "sshxm")
public class Permission implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pername;
	private String url;
	private Set<Adminroles> adminroleses = new HashSet<Adminroles>(0);

	// Constructors

	/** default constructor */
	public Permission() {
	}

	/** full constructor */
	public Permission(String pername, String url, Set<Adminroles> adminroleses) {
		this.pername = pername;
		this.url = url;
		this.adminroleses = adminroleses;
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

	@Column(name = "pername")
	public String getPername() {
		return this.pername;
	}

	public void setPername(String pername) {
		this.pername = pername;
	}

	@Column(name = "url")
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "adminrolesper", catalog = "sshxm", joinColumns = { @JoinColumn(name = "permission", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "adminroles", nullable = false, updatable = false) })
	public Set<Adminroles> getAdminroleses() {
		return this.adminroleses;
	}

	public void setAdminroleses(Set<Adminroles> adminroleses) {
		this.adminroleses = adminroleses;
	}

}