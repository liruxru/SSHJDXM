package com.oracle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Customerorderitems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customerorderitems", catalog = "sshxm")
public class Customerorderitems implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private Customerorders customerorders;
	private Goods goods;
	private Integer goodsnum;
	private BigDecimal price;
	private BigDecimal subtotal;

	// Constructors

	/** default constructor */
	public Customerorderitems() {
	}

	/** full constructor */
	public Customerorderitems(Rooms rooms, Customerorders customerorders,
			Goods goods, Integer goodsnum, BigDecimal price, BigDecimal subtotal) {
		this.rooms = rooms;
		this.customerorders = customerorders;
		this.goods = goods;
		this.goodsnum = goodsnum;
		this.price = price;
		this.subtotal = subtotal;
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
	@JoinColumn(name = "orders")
	public Customerorders getCustomerorders() {
		return this.customerorders;
	}

	public void setCustomerorders(Customerorders customerorders) {
		this.customerorders = customerorders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "goods")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "goodsnum")
	public Integer getGoodsnum() {
		return this.goodsnum;
	}

	public void setGoodsnum(Integer goodsnum) {
		this.goodsnum = goodsnum;
	}

	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "subtotal")
	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

}