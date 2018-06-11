package com.oracle.entity;

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
 * Usersnews entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "usersnews", catalog = "sshxm")
public class Usersnews implements java.io.Serializable {

	// Fields

	private Integer id;
	private Customerorders customerorders;
	private Staffs staffs;
	private String news;
	private String answer;

	// Constructors

	/** default constructor */
	public Usersnews() {
	}

	/** full constructor */
	public Usersnews(Customerorders customerorders, Staffs staffs, String news,
			String answer) {
		this.customerorders = customerorders;
		this.staffs = staffs;
		this.news = news;
		this.answer = answer;
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
	@JoinColumn(name = "customerorders")
	public Customerorders getCustomerorders() {
		return this.customerorders;
	}

	public void setCustomerorders(Customerorders customerorders) {
		this.customerorders = customerorders;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffs")
	public Staffs getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Staffs staffs) {
		this.staffs = staffs;
	}

	@Column(name = "news")
	public String getNews() {
		return this.news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	@Column(name = "answer")
	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}