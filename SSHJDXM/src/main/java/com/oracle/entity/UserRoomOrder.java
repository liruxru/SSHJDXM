package com.oracle.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userroomorder", catalog = "sshxm")
public class UserRoomOrder implements java.io.Serializable {
	private Integer id;
	private Customerorders  customerorders;
	private Usersputong userputong;
	private Timestamp createdate;
	private Timestamp moditfydate;
	
	
	public UserRoomOrder() {
		// TODO Auto-generated constructor stub
	}
	public UserRoomOrder(Customerorders customerorders, Usersputong userputong) {
		super();
		this.customerorders = customerorders;
		this.userputong = userputong;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roomorder")
	public Customerorders getCustomerorders() {
		return customerorders;
	}
	public void setCustomerorders(Customerorders customerorders) {
		this.customerorders = customerorders;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userputong")
	public Usersputong getUserputong() {
		return userputong;
	}
	public void setUserputong(Usersputong userputong) {
		this.userputong = userputong;
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
