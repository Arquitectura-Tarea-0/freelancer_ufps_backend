package com.arqui.ufps.freelancer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the service_attendaces database table.
 * 
 */
@Entity
@Table(name="service_attendaces")
@NamedQuery(name="ServiceAttendace.findAll", query="SELECT s FROM ServiceAttendace s")
public class ServiceAttendace implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	@Column(name="service_details")
	private String serviceDetails;

	private BigDecimal state;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="serviceAttendace")
	private List<File> files;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public ServiceAttendace() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getServiceDetails() {
		return this.serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public List<File> getFiles() {
		return this.files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File addFile(File file) {
		getFiles().add(file);
		file.setServiceAttendace(this);

		return file;
	}

	public File removeFile(File file) {
		getFiles().remove(file);
		file.setServiceAttendace(null);

		return file;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}