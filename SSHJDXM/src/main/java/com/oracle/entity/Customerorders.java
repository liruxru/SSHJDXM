package com.oracle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

/**
 * Customerorders entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerorders", catalog = "sshxm")
public class Customerorders implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private Orderstatus orderstatus;
	private Usersputong usersputong;
	private String sn;
	private BigDecimal sum;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Usersnews> usersnewses = new HashSet<Usersnews>(0);
	private Set<Customerorderitems> customerorderitemses = new HashSet<Customerorderitems>(
			0);
	private Set<UserRoomOrder> userRoomOrder = new HashSet<UserRoomOrder>(
			0);
	
	

	// Constructors


	/** default constructor */
	public Customerorders() {
	}

	/** full constructor */
	public Customerorders(Rooms rooms, Orderstatus orderstatus, Usersputong usersputong,
			String sn, BigDecimal sum, Timestamp createdate,
			Set<Usersnews> usersnewses,
			Set<Customerorderitems> customerorderitemses) {
		this.rooms = rooms;
		this.orderstatus = orderstatus;
		this.usersputong = usersputong;
		this.sn = sn;
		this.sum = sum;
		this.createdate = createdate;
		this.usersnewses = usersnewses;
		this.customerorderitemses = customerorderitemses;
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
	@JoinColumn(name = "status")
	public Orderstatus getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(Orderstatus orderstatus) {
		this.orderstatus = orderstatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usersputong")
	public Usersputong getUsersputong() {
		return usersputong;
	}
	public void setUsersputong(Usersputong usersputong) {
		this.usersputong = usersputong;
	}


	@Column(name = "sn")
	public String getSn() {
		return this.sn;
	}



	public void setSn(String sn) {
		this.sn = sn;
	}

	@Column(name = "sum", precision = 10)
	public BigDecimal getSum() {
		return this.sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	@Column(name = "createdate", length = 19)
	public Timestamp getCreatedate() {
		return this.createdate;
	}

	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerorders")
	public Set<Usersnews> getUsersnewses() {
		return this.usersnewses;
	}

	public void setUsersnewses(Set<Usersnews> usersnewses) {
		this.usersnewses = usersnewses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerorders")
	@OrderBy(clause="id asc")
	public Set<Customerorderitems> getCustomerorderitemses() {
		return this.customerorderitemses;
	}

	public void setCustomerorderitemses(
			Set<Customerorderitems> customerorderitemses) {
		this.customerorderitemses = customerorderitemses;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "customerorders")
	@OrderBy(clause="createdate asc")
	public Set<UserRoomOrder> getUserRoomOrder() {
		return userRoomOrder;
	}

	public void setUserRoomOrder(Set<UserRoomOrder> userRoomOrder) {
		this.userRoomOrder = userRoomOrder;
	}
	
	
	@Column(name = "moditfydate", length = 19)
	public Timestamp getModitfydate() {
		return this.moditfydate;
	}

	public void setModitfydate(Timestamp moditfydate) {
		this.moditfydate = moditfydate;
	}


}