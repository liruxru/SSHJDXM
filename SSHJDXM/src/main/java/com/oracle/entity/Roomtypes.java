package com.oracle.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Roomtypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roomtypes", catalog = "sshxm")
public class Roomtypes implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roomtype;
	private Double price;
	private Set<Rooms> roomses = new HashSet<Rooms>(0);

	// Constructors

	/** default constructor */
	public Roomtypes() {
	}

	/** full constructor */
	public Roomtypes(String roomtype, Double price, Set<Rooms> roomses) {
		this.roomtype = roomtype;
		this.price = price;
		this.roomses = roomses;
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

	@Column(name = "roomtype")
	public String getRoomtype() {
		return this.roomtype;
	}

	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	@Column(name = "price", precision = 10)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roomtypes")
	public Set<Rooms> getRoomses() {
		return this.roomses;
	}

	public void setRoomses(Set<Rooms> roomses) {
		this.roomses = roomses;
	}

}