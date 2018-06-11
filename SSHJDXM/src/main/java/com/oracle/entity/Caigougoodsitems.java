package com.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Caigougoodsitems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "caigougoodsitems", catalog = "sshxm")
public class Caigougoodsitems implements java.io.Serializable {

	// Fields

	private Integer id;
	private Goods goods;
	private Caigou caigou;
	private Integer goodsnum;

	// Constructors

	/** default constructor */
	public Caigougoodsitems() {
	}

	/** full constructor */
	public Caigougoodsitems(Goods goods, Caigou caigou, Integer goodsnum) {
		this.goods = goods;
		this.caigou = caigou;
		this.goodsnum = goodsnum;
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
	@JoinColumn(name = "goods")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caigou")
	public Caigou getCaigou() {
		return this.caigou;
	}

	public void setCaigou(Caigou caigou) {
		this.caigou = caigou;
	}

	@Column(name = "goodsnum")
	public Integer getGoodsnum() {
		return this.goodsnum;
	}

	public void setGoodsnum(Integer goodsnum) {
		this.goodsnum = goodsnum;
	}

}