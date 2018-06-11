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

import org.springframework.stereotype.Component;

/**
 * Repair entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "repair", catalog = "sshxm")
@Component
public class Repair implements java.io.Serializable {

	// Fields

	private Integer id;
	private Rooms rooms;
	private Ditty ditty;
	private String description;
	private Integer num;
	private Timestamp createdate;
	private Timestamp moditfydate;

	// Constructors

	/** default constructor */
	public Repair() {
	}

	/** full constructor */
	public Repair(Rooms rooms, Ditty ditty, String description, Integer num,
			Timestamp createdate, Timestamp moditfydate) {
		this.rooms = rooms;
		this.ditty = ditty;
		this.description = description;
		this.num = num;
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
	@JoinColumn(name = "ditty")
	public Ditty getDitty() {
		return this.ditty;
	}

	public void setDitty(Ditty ditty) {
		this.ditty = ditty;
	}

	@Column(name = "description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
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