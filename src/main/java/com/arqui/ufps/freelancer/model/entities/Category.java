package com.arqui.ufps.freelancer.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	private String name;

	private String role;

	//bi-directional many-to-one association to ServiceOffer
	@OneToMany(mappedBy="category")
	private List<ServiceOffer> serviceOffers;

	//bi-directional many-to-one association to ServiceRequest
	@OneToMany(mappedBy="category")
	private List<ServiceRequest> serviceRequests;

	public Category() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ServiceOffer> getServiceOffers() {
		return this.serviceOffers;
	}

	public void setServiceOffers(List<ServiceOffer> serviceOffers) {
		this.serviceOffers = serviceOffers;
	}

	public ServiceOffer addServiceOffer(ServiceOffer serviceOffer) {
		getServiceOffers().add(serviceOffer);
		serviceOffer.setCategory(this);

		return serviceOffer;
	}

	public ServiceOffer removeServiceOffer(ServiceOffer serviceOffer) {
		getServiceOffers().remove(serviceOffer);
		serviceOffer.setCategory(null);

		return serviceOffer;
	}

	public List<ServiceRequest> getServiceRequests() {
		return this.serviceRequests;
	}

	public void setServiceRequests(List<ServiceRequest> serviceRequests) {
		this.serviceRequests = serviceRequests;
	}

	public ServiceRequest addServiceRequest(ServiceRequest serviceRequest) {
		getServiceRequests().add(serviceRequest);
		serviceRequest.setCategory(this);

		return serviceRequest;
	}

	public ServiceRequest removeServiceRequest(ServiceRequest serviceRequest) {
		getServiceRequests().remove(serviceRequest);
		serviceRequest.setCategory(null);

		return serviceRequest;
	}

}