package com.oracle.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

/**
 * Usersputong entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usersputong", catalog = "sshxm")
public class Usersputong implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Sexs sex;
	private String idcard;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<Roomusers> roomuserses = new HashSet<Roomusers>(0);
	private Set<Customerorders> customerorderses = new HashSet<Customerorders>(
			0);
	private Set<UserRoomOrder> userRoomOrder = new HashSet<UserRoomOrder>(
			0);
	
	
	// Constructors



	/** default constructor */
	public Usersputong() {
	}

	/** full constructor */
	public Usersputong(String name, Sexs sex, String idcard,
			Set<Users> userses,Set<Roomusers> roomuserses) {
		this.name = name;
		this.sex = sex;
		this.idcard = idcard;
		this.userses = userses;
		this.roomuserses = roomuserses;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sex")
	public Sexs getSex() {
		return sex;
	}

	public void setSex(Sexs sex) {
		this.sex = sex;
	}



	@Column(name = "idcard")
	public String getIdcard() {
		return this.idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersputong")
	@OrderBy(clause="createdate asc")
	public Set<Roomusers> getRoomuserses() {
		return this.roomuserses;
	}

	public void setRoomuserses(Set<Roomusers> roomuserses) {
		this.roomuserses = roomuserses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersputong")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "usersputong")
	public Set<Customerorders> getCustomerorderses() {
		return this.customerorderses;
	}

	public void setCustomerorderses(Set<Customerorders> customerorderses) {
		this.customerorderses = customerorderses;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userputong")
	@OrderBy(clause="createdate asc")
	public Set<UserRoomOrder> getUserRoomOrder() {
		return userRoomOrder;
	}

	public void setUserRoomOrder(Set<UserRoomOrder> userRoomOrder) {
		this.userRoomOrder = userRoomOrder;
	}

}