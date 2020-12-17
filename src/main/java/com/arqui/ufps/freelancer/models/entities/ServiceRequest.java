package com.arqui.ufps.freelancer.models.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the service_request database table.
 * 
 */
@Entity
@Table(name="service_request")
@NamedQuery(name="ServiceRequest.findAll", query="SELECT s FROM ServiceRequest s")
public class ServiceRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int duration;

	private BigDecimal price;

	private String state;

	@Lob
	@Column(name="terms_and_conditions")
	private String termsAndConditions;

	@Column(name="terms_service")
	private String termsService;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Category
	@ManyToOne
	private Category category;

	public ServiceRequest() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTermsAndConditions() {
		return this.termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public String getTermsService() {
		return this.termsService;
	}

	public void setTermsService(String termsService) {
		this.termsService = termsService;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}