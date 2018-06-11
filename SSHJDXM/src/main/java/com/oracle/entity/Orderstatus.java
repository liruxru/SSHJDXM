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
 * Orderstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "orderstatus", catalog = "sshxm")
public class Orderstatus implements java.io.Serializable {

	// Fields

	private Integer id;
	private String orderstatus;
	private Set<Customerorders> customerorderses = new HashSet<Customerorders>(
			0);

	// Constructors

	/** default constructor */
	public Orderstatus() {
	}

	/** full constructor */
	public Orderstatus(String orderstatus, Set<Customerorders> customerorderses) {
		this.orderstatus = orderstatus;
		this.customerorderses = customerorderses;
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

	@Column(name = "orderstatus")
	public String getOrderstatus() {
		return this.orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "orderstatus")
	public Set<Customerorders> getCustomerorderses() {
		return this.customerorderses;
	}

	public void setCustomerorderses(Set<Customerorders> customerorderses) {
		this.customerorderses = customerorderses;
	}

}