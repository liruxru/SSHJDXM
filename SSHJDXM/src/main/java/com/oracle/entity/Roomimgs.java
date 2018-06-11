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
 * Roomimgs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "roomimgs", catalog = "sshxm")
public class Roomimgs implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private String roomimg;
	private Timestamp createdate;
	private Timestamp moditfydate;

	// Constructors

	/** default constructor */
	public Roomimgs() {
	}

	/** full constructor */
	public Roomimgs(Rooms rooms, String roomimg, Timestamp createdate,
			Timestamp moditfydate) {
		this.rooms = rooms;
		this.roomimg = roomimg;
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
	@JoinColumn(name = "rooms")
	public Rooms getRooms() {
		return this.rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

	@Column(name = "roomimg")
	public String getRoomimg() {
		return this.roomimg;
	}

	public void setRoomimg(String roomimg) {
		this.roomimg = roomimg;
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