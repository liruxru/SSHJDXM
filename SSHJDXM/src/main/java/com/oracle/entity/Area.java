package com.oracle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Area entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "area", catalog = "sshxm")
public class Area implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String name;
	private String citycode;

	// Constructors

	/** default constructor */
	public Area() {
	}

	/** full constructor */
	public Area(String code, String name, String citycode) {
		this.code = code;
		this.name = name;
		this.citycode = citycode;
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

	@Column(name = "citycode", length = 18)
	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

}