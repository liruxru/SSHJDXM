package com.oracle.entity;

import java.math.BigDecimal;
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

import org.hibernate.annotations.OrderBy;

/**
 * Rooms entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "rooms", catalog = "sshxm")
public class Rooms implements java.io.Serializable {

	// Fields

	private Integer id;
	private Roomtypes roomtypes;
	private String no;
	private BigDecimal deposit;
	private String phone;
	private String imgcover;
	private Roomstatus roomstatus;
	private Integer position;
	private String ip;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Roomusers> roomuserses = new HashSet<Roomusers>(0);
	private Set<Customerorders> customerorderses = new HashSet<Customerorders>(
			0);
	private Set<Customercart> customercarts = new HashSet<Customercart>(0);
	private Set<Customerorderitems> customerorderitemses = new HashSet<Customerorderitems>(
			0);
	private Set<Roomselect> roomselects = new HashSet<Roomselect>(0);
	private Set<Roomimgs> roomimgses = new HashSet<Roomimgs>(0);
	private Set<Repair> repairs = new HashSet<Repair>(0);

	// Constructors

	/** default constructor */
	public Rooms() {
	}

	/** full constructor */
	public Rooms(Roomtypes roomtypes, String no, BigDecimal deposit, String phone,
			String imgcover, Roomstatus roomstatus, Integer position,
			Timestamp createdate, Timestamp moditfydate,
			Set<Roomusers> roomuserses, Set<Customerorders> customerorderses,
			Set<Customercart> customercarts,
			Set<Customerorderitems> customerorderitemses,
			Set<Roomselect> roomselects, Set<Roomimgs> roomimgses,
			Set<Repair> repairs) {
		this.roomtypes = roomtypes;
		this.no = no;
		this.deposit = deposit;
		this.phone = phone;
		this.imgcover = imgcover;
		this.roomstatus = roomstatus;
		this.position = position;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		this.roomuserses = roomuserses;
		this.customerorderses = customerorderses;
		this.customercarts = customercarts;
		this.customerorderitemses = customerorderitemses;
		this.roomselects = roomselects;
		this.roomimgses = roomimgses;
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
	@JoinColumn(name = "roomtype")
	public Roomtypes getRoomtypes() {
		return this.roomtypes;
	}

	public void setRoomtypes(Roomtypes roomtypes) {
		this.roomtypes = roomtypes;
	}

	@Column(name = "no")
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name = "deposit", precision = 10)
	public BigDecimal getDeposit() {
		return this.deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "imgcover")
	public String getImgcover() {
		return this.imgcover;
	}

	public void setImgcover(String imgcover) {
		this.imgcover = imgcover;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomstatus")
	public Roomstatus getRoomstatus() {
		return this.roomstatus;
	}

	public void setRoomstatus(Roomstatus roomstatus) {
		this.roomstatus = roomstatus;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	@OrderBy(clause="createdate asc")
	public Set<Roomusers> getRoomuserses() {
		return this.roomuserses;
	}

	public void setRoomuserses(Set<Roomusers> roomuserses) {
		this.roomuserses = roomuserses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	@OrderBy(clause ="id asc")
	public Set<Customerorders> getCustomerorderses() {
		return this.customerorderses;
	}

	public void setCustomerorderses(Set<Customerorders> customerorderses) {
		this.customerorderses = customerorderses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	public Set<Customercart> getCustomercarts() {
		return this.customercarts;
	}

	public void setCustomercarts(Set<Customercart> customercarts) {
		this.customercarts = customercarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	public Set<Customerorderitems> getCustomerorderitemses() {
		return this.customerorderitemses;
	}

	public void setCustomerorderitemses(
			Set<Customerorderitems> customerorderitemses) {
		this.customerorderitemses = customerorderitemses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	public Set<Roomselect> getRoomselects() {
		return this.roomselects;
	}

	public void setRoomselects(Set<Roomselect> roomselects) {
		this.roomselects = roomselects;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	public Set<Roomimgs> getRoomimgses() {
		return this.roomimgses;
	}

	public void setRoomimgses(Set<Roomimgs> roomimgses) {
		this.roomimgses = roomimgses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rooms")
	public Set<Repair> getRepairs() {
		return this.repairs;
	}

	public void setRepairs(Set<Repair> repairs) {
		this.repairs = repairs;
	}

}