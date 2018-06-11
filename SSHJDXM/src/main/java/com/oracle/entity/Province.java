package com.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Province entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "province", catalog = "sshxm")
public class Province implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String name;

	// Constructors

	/** default constructor */
	public Province() {
	}

	/** full constructor */
	public Province(String code, String name) {
		this.code = code;
		this.name = name;
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

}