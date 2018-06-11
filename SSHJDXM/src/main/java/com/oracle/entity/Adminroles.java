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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * Adminroles entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "adminroles", catalog = "sshxm")
public class Adminroles implements java.io.Serializable {

	// Fields

	private Integer id;
	private String rolename;
	private String roledescription;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Admins> adminses = new HashSet<Admins>(0);
	private Set<Permission> permissions = new HashSet<Permission>(0);

	// Constructors

	/** default constructor */
	public Adminroles() {
	}

	/** full constructor */
	public Adminroles(String rolename, String roledescription,
			Timestamp createdate, Timestamp moditfydate, Set<Admins> adminses,
			Set<Permission> permissions) {
		this.rolename = rolename;
		this.roledescription = roledescription;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		this.adminses = adminses;
		this.permissions = permissions;
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

	@Column(name = "rolename")
	public String getRolename() {
		return this.rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	@Column(name = "roledescription")
	public String getRoledescription() {
		return this.roledescription;
	}

	public void setRoledescription(String roledescription) {
		this.roledescription = roledescription;
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

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "adminroleses")
	public Set<Admins> getAdminses() {
		return this.adminses;
	}

	public void setAdminses(Set<Admins> adminses) {
		this.adminses = adminses;
	}

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "adminroleses")
	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}