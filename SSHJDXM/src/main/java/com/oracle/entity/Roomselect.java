package com.oracle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Roomselect entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roomselect", catalog = "sshxm")
public class Roomselect implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private Users users;
	private String chargemethod;
	private BigDecimal price;
	private Timestamp createdate;
	private Timestamp enddate;

	// Constructors

	/** default constructor */
	public Roomselect() {
	}

	/** full constructor */
	public Roomselect(Rooms rooms, Users users, String chargemethod,
			BigDecimal price, Timestamp createdate, Timestamp enddate) {
		this.rooms = rooms;
		this.users = users;
		this.chargemethod = chargemethod;
		this.price = price;
		this.createdate = createdate;
		this.enddate = enddate;
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
	@JoinColumn(name = "room")
	public Rooms getRooms() {
		return this.rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "chargemethod")
	public String getChargemethod() {
		return this.chargemethod;
	}

	public void setChargemethod(String chargemethod) {
		this.chargemethod = chargemethod;
	}

	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "createdate", length = 19)
	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@Column(name = "enddate", length = 19)
	public Timestamp getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}

}