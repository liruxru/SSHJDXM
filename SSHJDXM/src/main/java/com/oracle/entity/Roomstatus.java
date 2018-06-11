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
 * Roomstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roomstatus", catalog = "sshxm")
public class Roomstatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String status;
	private Set<Rooms> roomses = new HashSet<Rooms>(0);

	// Constructors

	/** default constructor */
	public Roomstatus() {
	}

	/** full constructor */
	public Roomstatus(String status, Set<Rooms> roomses) {
		super();
		this.status = status;
		this.roomses = roomses;
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

	@Column(name = "status")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roomstatus")
	public Set<Rooms> getRoomses() {
		return this.roomses;
	}

	public void setRoomses(Set<Rooms> roomses) {
		this.roomses = roomses;
	}

}