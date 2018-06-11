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
 * Caigoudittyitems entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "caigoudittyitems", catalog = "sshxm")
public class Caigoudittyitems implements java.io.Serializable {

	// Fields

	private Integer id;
	private Ditty ditty;
	private Caigou caigou;
	private Integer dittynum;

	// Constructors

	/** default constructor */
	public Caigoudittyitems() {
	}

	/** full constructor */
	public Caigoudittyitems(Ditty ditty, Caigou caigou, Integer dittynum) {
		this.ditty = ditty;
		this.caigou = caigou;
		this.dittynum = dittynum;
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
	@JoinColumn(name = "ditty")
	public Ditty getDitty() {
		return this.ditty;
	}

	public void setDitty(Ditty ditty) {
		this.ditty = ditty;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caigou")
	public Caigou getCaigou() {
		return this.caigou;
	}

	public void setCaigou(Caigou caigou) {
		this.caigou = caigou;
	}

	@Column(name = "dittynum")
	public Integer getDittynum() {
		return this.dittynum;
	}

	public void setDittynum(Integer dittynum) {
		this.dittynum = dittynum;
	}

}