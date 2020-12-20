package com.arqui.ufps.freelancer.model.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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

	@Temporal(TemporalType.DATE)
	@Column(name="created_at")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdAt;

	private String email;

//	@JsonIgnore
	private String password;

	private String phone;

	private String role;
	
	
	public User(Date createdAt, String email, String password, String phone, String role) {
		super();
		this.createdAt = createdAt;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = role;
	}

	//bi-directional many-to-one association to CurriculumVitae
	@OneToMany(mappedBy="user")
	@JsonManagedReference(value = "curriculumStudent")
	private List<CurriculumVitae> curriculumVitaes;

	//bi-directional many-to-one association to ServiceAttendace
	@JsonManagedReference(value = "attendanceStudent")
	@OneToMany(mappedBy="studentId")
	private List<ServiceAttendace> serviceAttendacesStudent;

	//bi-directional many-to-one association to ServiceAttendace
	@JsonManagedReference(value = "attendanceContractor")
	@OneToMany(mappedBy="contractorId")
	private List<ServiceAttendace> serviceAttendacesContractor;

	//bi-directional many-to-one association to ServiceOffer
	@JsonManagedReference(value = "offerUser")
	@OneToMany(mappedBy="user")
	private List<ServiceOffer> serviceOffers;

	//bi-directional many-to-one association to ServiceRequest
	@JsonManagedReference(value = "requestUser")
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

	public List<ServiceAttendace> getServiceAttendacesStudent() {
		return serviceAttendacesStudent;
	}

	public void setServiceAttendacesStudent(List<ServiceAttendace> serviceAttendacesStudent) {
		this.serviceAttendacesStudent = serviceAttendacesStudent;
	}

	public List<ServiceAttendace> getServiceAttendacesContractor() {
		return serviceAttendacesContractor;
	}

	public void setServiceAttendacesContractor(List<ServiceAttendace> serviceAttendacesContractor) {
		this.serviceAttendacesContractor = serviceAttendacesContractor;
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