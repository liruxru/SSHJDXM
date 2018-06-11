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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Admins entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "admins", catalog = "sshxm")
public class Admins implements java.io.Serializable {

	// Fields

	private Integer id;
	private Staffs staffs;
	private String loginname;
	private String password;
	private Timestamp logindate;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Adminroles> adminroleses = new HashSet<Adminroles>(0);

	// Constructors

	/** default constructor */
	public Admins() {
	}

	/** full constructor */
	public Admins(Staffs staffs, String loginname, String password,
			Timestamp logindate, Timestamp createdate, Timestamp moditfydate,
			Set<Adminroles> adminroleses) {
		this.staffs = staffs;
		this.loginname = loginname;
		this.password = password;
		this.logindate = logindate;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffs")
	public Staffs getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}

	@Column(name = "loginname")
	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "logindate", length = 19)
	public Timestamp getLogindate() {
		return this.logindate;
	}

	public void setLogindate(Timestamp logindate) {
		this.logindate = logindate;
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

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "adminadminrolse", catalog = "sshxm", joinColumns = { @JoinColumn(name = "admins", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "adminroles", nullable = false, updatable = false) })
	public Set<Adminroles> getAdminroleses() {
		return this.adminroleses;
	}

	public void setAdminroleses(Set<Adminroles> adminroleses) {
		this.adminroleses = adminroleses;
	}

}