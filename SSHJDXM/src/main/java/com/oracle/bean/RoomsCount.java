package com.oracle.bean;

import org.springframework.stereotype.Component;

@Component
public class RoomsCount {
	private Long allRooms;//全部房间
	private Long nullRooms;//空房间
	private Long dirtyRooms;//脏房间
	private Long propleRooms;//住人
	private Long peopleDirtyRooms;//住人脏
	private Long yudingRooms;//预定
	private Long repairRooms;//维修
	private Long noMakeRooms;//不可用
	public Long getAllRooms() {
		return allRooms;
	}
	public void setAllRooms(Long allRooms) {
		this.allRooms = allRooms;
	}
	public Long getNullRooms() {
		return nullRooms;
	}
	public void setNullRooms(Long nullRooms) {
		this.nullRooms = nullRooms;
	}
	public Long getPropleRooms() {
		return propleRooms;
	}
	public void setPropleRooms(Long propleRooms) {
		this.propleRooms = propleRooms;
	}
	public Long getDirtyRooms() {
		return dirtyRooms;
	}
	public void setDirtyRooms(Long dirtyRooms) {
		this.dirtyRooms = dirtyRooms;
	}
	public Long getPeopleDirtyRooms() {
		return peopleDirtyRooms;
	}
	public void setPeopleDirtyRooms(Long peopleDirtyRooms) {
		this.peopleDirtyRooms = peopleDirtyRooms;
	}
	public Long getYudingRooms() {
		return yudingRooms;
	}
	public void setYudingRooms(Long yudingRooms) {
		this.yudingRooms = yudingRooms;
	}
	public Long getRepairRooms() {
		return repairRooms;
	}
	public void setRepairRooms(Long repairRooms) {
		this.repairRooms = repairRooms;
	}
	public Long getNoMakeRooms() {
		return noMakeRooms;
	}
	public void setNoMakeRooms(Long noMakeRooms) {
		this.noMakeRooms = noMakeRooms;
	}
	public RoomsCount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RoomsCount(Long allRooms, Long nullRooms, Long dirtyRooms,
			Long propleRooms, Long peopleDirtyRooms, Long yudingRooms,
			Long repairRooms, Long noMakeRooms) {
		super();
		this.allRooms = allRooms;
		this.nullRooms = nullRooms;
		this.dirtyRooms = dirtyRooms;
		this.propleRooms = propleRooms;
		this.peopleDirtyRooms = peopleDirtyRooms;
		this.yudingRooms = yudingRooms;
		this.repairRooms = repairRooms;
		this.noMakeRooms = noMakeRooms;
	}
	
}
