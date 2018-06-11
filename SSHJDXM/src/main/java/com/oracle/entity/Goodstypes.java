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
 * Goodstypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goodstypes", catalog = "sshxm")
public class Goodstypes implements java.io.Serializable {

	// Fields

	private Integer id;
	private String typename;
	private Timestamp createdate;
	private Timestamp moditfydate;
	private Set<Goods> goodses = new HashSet<Goods>(0);

	// Constructors

	/** default constructor */
	public Goodstypes() {
	}

	/** full constructor */
	public Goodstypes(String typename, Timestamp createdate,
			Timestamp moditfydate, Set<Goods> goodses) {
		this.typename = typename;
		this.createdate = createdate;
		this.moditfydate = moditfydate;
		this.goodses = goodses;
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

	@Column(name = "typename")
	public String getTypename() {
		return this.typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "goodstypes")
	public Set<Goods> getGoodses() {
		return this.goodses;
	}

	public void setGoodses(Set<Goods> goodses) {
		this.goodses = goodses;
	}

}