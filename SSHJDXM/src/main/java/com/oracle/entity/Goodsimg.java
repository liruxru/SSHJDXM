package com.oracle.entity;

import java.sql.Timestamp;
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
 * Goodsimg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goodsimg", catalog = "sshxm")
public class Goodsimg implements java.io.Serializable {

	// Fields

	private Integer id;
	private Goods goods;
	private String goodsimg;
	private Timestamp createdate;
	private Timestamp moditfydate;

	// Constructors

	/** default constructor */
	public Goodsimg() {
	}

	/** full constructor */
	public Goodsimg(Goods goods, String goodsimg, Timestamp createdate,
			Timestamp moditfydate) {
		this.goods = goods;
		this.goodsimg = goodsimg;
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
	@JoinColumn(name = "goods")
	public Goods getGoods() {
		return this.goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Column(name = "goodsimg")
	public String getGoodsimg() {
		return this.goodsimg;
	}

	public void setGoodsimg(String goodsimg) {
		this.goodsimg = goodsimg;
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