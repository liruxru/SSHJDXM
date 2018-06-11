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

/**
 * Goods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods", catalog = "sshxm")
public class Goods implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer statues;
	private Entrepot entrepot;
	private Provider provider;
	private Goodstypes goodstypes;
	private String name;
	private String fullname;
	private Integer num;
	private BigDecimal salePrice;
	private BigDecimal price;
	private String imgcover;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Goodsimg> goodsimgs = new HashSet<Goodsimg>(0);
	private Set<Customercart> customercarts = new HashSet<Customercart>(0);
	private Set<Customerorderitems> customerorderitemses = new HashSet<Customerorderitems>(
			0);
	private Set<Caigougoodsitems> caigougoodsitemses = new HashSet<Caigougoodsitems>(
			0);

	// Constructors

	/** default constructor */
	public Goods() {
	}

	/** full constructor */
	public Goods(Entrepot entrepot, Provider provider, Goodstypes goodstypes,
			String name, String fullname,Integer statues, Integer num,BigDecimal salePrice,BigDecimal price,
			String imgcover, Timestamp createdate, Timestamp moditfydate,
			Set<Goodsimg> goodsimgs, Set<Customercart> customercarts,
			Set<Customerorderitems> customerorderitemses,
			Set<Caigougoodsitems> caigougoodsitemses) {
		this.entrepot = entrepot;
		this.provider = provider;
		this.goodstypes = goodstypes;
		this.name = name;
		this.fullname = fullname;
		this.num = num;
		this.statues=statues;
		this.salePrice= salePrice;
		this.price = price;
		this.imgcover = imgcover;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		this.goodsimgs = goodsimgs;
		this.customercarts = customercarts;
		this.customerorderitemses = customerorderitemses;
		this.caigougoodsitemses = caigougoodsitemses;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", statues=" + statues + ", entrepot="
				+ entrepot + ", provider=" + provider + ", goodstypes="
				+ goodstypes + ", name=" + name + ", fullname=" + fullname
				+ ", num=" + num + ", salePrice=" + salePrice + ", price="
				+ price + ", imgcover=" + imgcover + ", createdate="
				+ createdate + ", moditfydate=" + moditfydate + ", goodsimgs="
				+ goodsimgs + ", customercarts=" + customercarts
				+ ", customerorderitemses=" + customerorderitemses
				+ ", caigougoodsitemses=" + caigougoodsitemses + "]";
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
	@JoinColumn(name = "entrepot")
	public Entrepot getEntrepot() {
		return this.entrepot;
	}

	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "provider")
	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "type")
	public Goodstypes getGoodstypes() {
		return this.goodstypes;
	}

	public void setGoodstypes(Goodstypes goodstypes) {
		this.goodstypes = goodstypes;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "fullname")
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	@Column(name="salePrice",precision = 10)
	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	@Column(name = "statues", precision = 10)
	public Integer getStatues() {
		return this.statues;
	}
	public void setStatues(Integer statues) {
		this.statues = statues;
	}
	
	@Column(name = "price", precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "imgcover")
	public String getImgcover() {
		return this.imgcover;
	}

	public void setImgcover(String imgcover) {
		this.imgcover = imgcover;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<Goodsimg> getGoodsimgs() {
		return this.goodsimgs;
	}

	public void setGoodsimgs(Set<Goodsimg> goodsimgs) {
		this.goodsimgs = goodsimgs;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<Customercart> getCustomercarts() {
		return this.customercarts;
	}

	public void setCustomercarts(Set<Customercart> customercarts) {
		this.customercarts = customercarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<Customerorderitems> getCustomerorderitemses() {
		return this.customerorderitemses;
	}

	public void setCustomerorderitemses(
			Set<Customerorderitems> customerorderitemses) {
		this.customerorderitemses = customerorderitemses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goods")
	public Set<Caigougoodsitems> getCaigougoodsitemses() {
		return this.caigougoodsitemses;
	}

	public void setCaigougoodsitemses(Set<Caigougoodsitems> caigougoodsitemses) {
		this.caigougoodsitemses = caigougoodsitemses;
	}

}