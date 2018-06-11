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
 * Customercart entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customercart", catalog = "sshxm")
public class Customercart implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private Users users;
	private Goods goods;
	private String goodsname;
	private Integer goodsnum;
	private BigDecimal totalsum;
	private Timestamp createdate;
	private Timestamp moditfydate;

	// Constructors

	/** default constructor */
	public Customercart() {
	}

	/** full constructor */
	public Customercart(Rooms rooms, Users users, Goods goods,
			String goodsname, Integer goodsnum, BigDecimal totalsum,
			Timestamp createdate, Timestamp moditfydate) {
		this.rooms = rooms;
		this.users = users;
		this.goods = goods;
		this.goodsname = goodsname;
		this.goodsnum = goodsnum;
		this.totalsum = totalsum;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "goodsname")
	public String getGoodsname() {
		return this.goodsname;
	}

	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}

	@Column(name = "goodsnum")
	public Integer getGoodsnum() {
		return this.goodsnum;
	}

	public void setGoodsnum(Integer goodsnum) {
		this.goodsnum = goodsnum;
	}

	@Column(name = "totalsum", precision = 10)
	public BigDecimal getTotalsum() {
		return this.totalsum;
	}

	public void setTotalsum(BigDecimal totalsum) {
		this.totalsum = totalsum;
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

}