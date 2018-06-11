package com.oracle.bean;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class RoomUserYuding {
	private String roomUserYudingName;
	private String roomUserYudingPhone;
	private Timestamp roomUserYudingcreateDate;
	private Timestamp roomUserYudingendDate;
	public String getRoomUserYudingName() {
		return roomUserYudingName;
	}
	public void setRoomUserYudingName(String roomUserYudingName) {
		this.roomUserYudingName = roomUserYudingName;
	}
	public String getRoomUserYudingPhone() {
		return roomUserYudingPhone;
	}
	public void setRoomUserYudingPhone(String roomUserYudingPhone) {
		this.roomUserYudingPhone = roomUserYudingPhone;
	}
	public Timestamp getRoomUserYudingcreateDate() {
		return roomUserYudingcreateDate;
	}
	public void setRoomUserYudingcreateDate(Timestamp roomUserYudingcreateDate) {
		this.roomUserYudingcreateDate = roomUserYudingcreateDate;
	}
	public Timestamp getRoomUserYudingendDate() {
		return roomUserYudingendDate;
	}
	public void setRoomUserYudingendDate(Timestamp roomUserYudingendDate) {
		this.roomUserYudingendDate = roomUserYudingendDate;
	}
	public RoomUserYuding(String roomUserYudingName,
			String roomUserYudingPhone, Timestamp roomUserYudingcreateDate,
			Timestamp roomUserYudingendDate) {
		super();
		this.roomUserYudingName = roomUserYudingName;
		this.roomUserYudingPhone = roomUserYudingPhone;
		this.roomUserYudingcreateDate = roomUserYudingcreateDate;
		this.roomUserYudingendDate = roomUserYudingendDate;
	}
	public RoomUserYuding() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
