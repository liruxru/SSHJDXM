package com.oracle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Assets entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "assets", catalog = "sshxm")
public class Assets implements java.io.Serializable {

	// Fields

	private Integer id;
	private BigDecimal sum;
	private Timestamp createdate;
	private Timestamp moditfydate;

	// Constructors

	/** default constructor */
	public Assets() {
	}

	/** full constructor */
	public Assets(BigDecimal sum, Timestamp createdate, Timestamp moditfydate) {
		this.sum = sum;
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

	@Column(name = "moditfydate", length = 19)
	public Timestamp getModitfydate() {
		return this.moditfydate;
	}

	public void setModitfydate(Timestamp moditfydate) {
		this.moditfydate = moditfydate;
	}

}