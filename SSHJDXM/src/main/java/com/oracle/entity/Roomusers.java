package com.oracle.entity;

import java.sql.Timestamp;
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
 * Roomusers entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roomusers", catalog = "sshxm")
public class Roomusers implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private Usersputong usersputong;
	private Timestamp createdate;
	private Timestamp moditfydate;

	// Constructors

	/** default constructor */
	public Roomusers() {
	}

	/** full constructor */
	public Roomusers(Rooms rooms, Usersputong usersputong, Timestamp createdate,
			Timestamp moditfydate) {
		this.rooms = rooms;
		this.usersputong = usersputong;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room")
	public Rooms getRooms() {
		return this.rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usersputong")
	public Usersputong getUsersputong() {
		return usersputong;
	}

	public void setUsersputong(Usersputong usersputong) {
		this.usersputong = usersputong;
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