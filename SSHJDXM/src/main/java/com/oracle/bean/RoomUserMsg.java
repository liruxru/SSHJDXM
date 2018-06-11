package com.oracle.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class RoomUserMsg {
	private String[] roomspeoples;
	private BigDecimal roomsyouhuiprice;
	private Timestamp roomszhudate;
	private Timestamp roomslidate;
	private Long roomsdays;
	private BigDecimal roomsgoodsprice;
	private BigDecimal roomsyajin;
	
	
	
	public String[] getRoomspeoples() {
		return roomspeoples;
	}
	public void setRoomspeoples(String[] roomspeoples) {
		this.roomspeoples = roomspeoples;
	}
	public BigDecimal getRoomsyouhuiprice() {
		return roomsyouhuiprice;
	}
	public void setRoomsyouhuiprice(BigDecimal roomsyouhuiprice) {
		this.roomsyouhuiprice = roomsyouhuiprice;
	}
	public Timestamp getRoomszhudate() {
		return roomszhudate;
	}
	public void setRoomszhudate(Timestamp roomszhudate) {
		this.roomszhudate = roomszhudate;
	}
	public Timestamp getRoomslidate() {
		return roomslidate;
	}
	public void setRoomslidate(Timestamp roomslidate) {
		this.roomslidate = roomslidate;
	}
	public Long getRoomsdays() {
		return roomsdays;
	}
	public void setRoomsdays(Long roomsdays) {
		this.roomsdays = roomsdays;
	}
	public BigDecimal getRoomsgoodsprice() {
		return roomsgoodsprice;
	}
	public void setRoomsgoodsprice(BigDecimal roomsgoodsprice) {
		this.roomsgoodsprice = roomsgoodsprice;
	}
	public BigDecimal getroomsyajin() {
		return roomsyajin;
	}
	public void setroomsyajin(BigDecimal roomsyajin) {
		this.roomsyajin = roomsyajin;
	}
	public RoomUserMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomUserMsg(String[] roomspeoples, BigDecimal roomsyouhuiprice,
			Timestamp roomszhudate, Timestamp roomslidate, Long roomsdays,
			BigDecimal roomsgoodsprice, BigDecimal roomsyajin) {
		super();
		this.roomspeoples = roomspeoples;
		this.roomsyouhuiprice = roomsyouhuiprice;
		this.roomszhudate = roomszhudate;
		this.roomslidate = roomslidate;
		this.roomsdays = roomsdays;
		this.roomsgoodsprice = roomsgoodsprice;
		this.roomsyajin = roomsyajin;
	}
	
	
	
	
}
