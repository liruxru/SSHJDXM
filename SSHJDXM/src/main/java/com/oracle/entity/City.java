package com.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "sshxm")
public class City implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String name;
	private String provincecode;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** full constructor */
	public City(String code, String name, String provincecode) {
		this.code = code;
		this.name = name;
		this.provincecode = provincecode;
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

	@Column(name = "code", length = 18)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "name", length = 60)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "provincecode", length = 18)
	public String getProvincecode() {
		return this.provincecode;
	}

	public void setProvincecode(String provincecode) {
		this.provincecode = provincecode;
	}

}