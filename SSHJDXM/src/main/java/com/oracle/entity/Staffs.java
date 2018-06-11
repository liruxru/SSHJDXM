package com.oracle.entity;

import java.sql.Timestamp;
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
 * Staffs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "staffs", catalog = "sshxm")
public class Staffs implements java.io.Serializable {

	// Fields

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
	private Set<Usersnews> usersnewses = new HashSet<Usersnews>(0);
	private Set<Entrepot> entrepots = new HashSet<Entrepot>(0);
	private Set<Caigou> caigous = new HashSet<Caigou>(0);
	private Set<Admins> adminses = new HashSet<Admins>(0);
	private Set<Assetsdetal> assetsdetals = new HashSet<Assetsdetal>(0);

	// Constructors

	/** default constructor */
	public Staffs() {
	}

	/** full constructor */
	public Staffs(String no, String name, Integer sex, Integer age,
			String phone, String address, String workdescription,
			Double salary, Timestamp createdate, Timestamp moditfydate,
			Set<Usersnews> usersnewses, Set<Entrepot> entrepots,
			Set<Caigou> caigous, Set<Admins> adminses,
			Set<Assetsdetal> assetsdetals) {
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
		this.usersnewses = usersnewses;
		this.entrepots = entrepots;
		this.caigous = caigous;
		this.adminses = adminses;
		this.assetsdetals = assetsdetals;
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

	@Column(name = "no")
	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex")
	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "workdescription")
	public String getWorkdescription() {
		return this.workdescription;
	}

	public void setWorkdescription(String workdescription) {
		this.workdescription = workdescription;
	}

	@Column(name = "salary", precision = 10)
	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Usersnews> getUsersnewses() {
		return this.usersnewses;
	}

	public void setUsersnewses(Set<Usersnews> usersnewses) {
		this.usersnewses = usersnewses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Entrepot> getEntrepots() {
		return this.entrepots;
	}

	public void setEntrepots(Set<Entrepot> entrepots) {
		this.entrepots = entrepots;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Caigou> getCaigous() {
		return this.caigous;
	}

	public void setCaigous(Set<Caigou> caigous) {
		this.caigous = caigous;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Admins> getAdminses() {
		return this.adminses;
	}

	public void setAdminses(Set<Admins> adminses) {
		this.adminses = adminses;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "staffs")
	public Set<Assetsdetal> getAssetsdetals() {
		return this.assetsdetals;
	}

	public void setAssetsdetals(Set<Assetsdetal> assetsdetals) {
		this.assetsdetals = assetsdetals;
	}

}