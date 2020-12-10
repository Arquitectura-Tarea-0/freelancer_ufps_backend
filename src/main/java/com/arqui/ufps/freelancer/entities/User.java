package com.arqui.ufps.freelancer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_user")
	private int idUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String email;

	private String password;

	private String phone;

	private String role;

	//bi-directional many-to-one association to CurriculumVitae
	@OneToMany(mappedBy="user")
	private List<CurriculumVitae> curriculumVitaes;

	//bi-directional many-to-one association to ServiceAttendace
	@OneToMany(mappedBy="user")
	private List<ServiceAttendace> serviceAttendaces;

	//bi-directional many-to-one association to ServiceOffer
	@OneToMany(mappedBy="user")
	private List<ServiceOffer> serviceOffers;

	//bi-directional many-to-one association to ServiceRequest
	@OneToMany(mappedBy="user")
	private List<ServiceRequest> serviceRequests;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<CurriculumVitae> getCurriculumVitaes() {
		return this.curriculumVitaes;
	}

	public void setCurriculumVitaes(List<CurriculumVitae> curriculumVitaes) {
		this.curriculumVitaes = curriculumVitaes;
	}

	public CurriculumVitae addCurriculumVitae(CurriculumVitae curriculumVitae) {
		getCurriculumVitaes().add(curriculumVitae);
		curriculumVitae.setUser(this);

		return curriculumVitae;
	}

	public CurriculumVitae removeCurriculumVitae(CurriculumVitae curriculumVitae) {
		getCurriculumVitaes().remove(curriculumVitae);
		curriculumVitae.setUser(null);

		return curriculumVitae;
	}

	public List<ServiceAttendace> getServiceAttendaces() {
		return this.serviceAttendaces;
	}

	public void setServiceAttendaces(List<ServiceAttendace> serviceAttendaces) {
		this.serviceAttendaces = serviceAttendaces;
	}

	public ServiceAttendace addServiceAttendace(ServiceAttendace serviceAttendace) {
		getServiceAttendaces().add(serviceAttendace);
		serviceAttendace.setUser(this);

		return serviceAttendace;
	}

	public ServiceAttendace removeServiceAttendace(ServiceAttendace serviceAttendace) {
		getServiceAttendaces().remove(serviceAttendace);
		serviceAttendace.setUser(null);

		return serviceAttendace;
	}

	public List<ServiceOffer> getServiceOffers() {
		return this.serviceOffers;
	}

	public void setServiceOffers(List<ServiceOffer> serviceOffers) {
		this.serviceOffers = serviceOffers;
	}

	public ServiceOffer addServiceOffer(ServiceOffer serviceOffer) {
		getServiceOffers().add(serviceOffer);
		serviceOffer.setUser(this);

		return serviceOffer;
	}

	public ServiceOffer removeServiceOffer(ServiceOffer serviceOffer) {
		getServiceOffers().remove(serviceOffer);
		serviceOffer.setUser(null);

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
		serviceRequest.setUser(this);

		return serviceRequest;
	}

	public ServiceRequest removeServiceRequest(ServiceRequest serviceRequest) {
		getServiceRequests().remove(serviceRequest);
		serviceRequest.setUser(null);

		return serviceRequest;
	}

}