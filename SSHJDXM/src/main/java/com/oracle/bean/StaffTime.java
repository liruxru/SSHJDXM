package com.oracle.bean;

import java.sql.Timestamp;

public class StaffTime {
	private Integer id;
	private String no;
	private String name;
	private Integer sex;
	private Integer age;
	private String phone;
	private String address;
	private String workdescription;
	private Double salary;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private String    times;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWorkdescription() {
		return workdescription;
	}
	public void setWorkdescription(String workdescription) {
		this.workdescription = workdescription;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Timestamp getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public Timestamp getModitfydate() {
		return moditfydate;
	}
	public void setModitfydate(Timestamp moditfydate) {
		this.moditfydate = moditfydate;
	}
	public String getTimes() {
		return times;
	}
	public void setTimes(String times) {
		this.times = times;
	}
	public StaffTime() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StaffTime(Integer id, String no, String name, Integer sex,
			Integer age, String phone, String address, String workdescription,
			Double salary, Timestamp createdate, Timestamp moditfydate,
			String times) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.workdescription = workdescription;
		this.salary = salary;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		this.times = times;
	}
	

}
