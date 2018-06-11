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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "sshxm")
public class Users implements java.io.Serializable {

	// Fields

	private Integer id;
	private Usersputong usersputong;
	private String loginname;
	private String password;
	private String username;
	private String age;
	private String phone;
	private String email;
	private String address;
	private Usersvip usersvip;
	private Long jifen;
	private Integer num;
	private Timestamp createdate;
	private Timestamp moditfydate;

	private Set<Customercart> customercarts = new HashSet<Customercart>(0);
	private Set<Roomselect> roomselects = new HashSet<Roomselect>(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** full constructor */
	public Users(Usersputong usersputong, String loginname, String password,String username,
			String age, String phone, String email, String address,Usersvip usersvip, Long jifen,
			Integer num, Timestamp createdate, Timestamp moditfydate,
			
			Set<Customercart> customercarts, Set<Roomselect> roomselects) {
		this.usersputong = usersputong;
		this.loginname = loginname;
		this.password = password;
		this.username = username;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.usersvip= usersvip;
		this.jifen = jifen;
		this.num = num;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		
		this.customercarts = customercarts;
		this.roomselects = roomselects;
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
	@JoinColumn(name = "usersputong")
	public Usersputong getUsersputong() {
		return this.usersputong;
	}

	public void setUsersputong(Usersputong usersputong) {
		this.usersputong = usersputong;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usersvip")	
	public Usersvip getUsersvip() {
		return usersvip;
	}

	public void setUsersvip(Usersvip usersvip) {
		this.usersvip = usersvip;
	}



	@Column(name = "loginname")
	public String getLoginname() {
		return this.loginname;
	}
	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	
	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "age")
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "jifen", precision = 11, scale = 0)
	public Long getJifen() {
		return this.jifen;
	}

	public void setJifen(Long jifen) {
		this.jifen = jifen;
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


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Customercart> getCustomercarts() {
		return this.customercarts;
	}

	public void setCustomercarts(Set<Customercart> customercarts) {
		this.customercarts = customercarts;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Roomselect> getRoomselects() {
		return this.roomselects;
	}

	public void setRoomselects(Set<Roomselect> roomselects) {
		this.roomselects = roomselects;
	}

}