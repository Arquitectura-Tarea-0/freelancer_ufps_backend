package com.arqui.ufps.freelancer.models.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;


/**
 * The persistent class for the files database table.
 * 
 */
@Entity
@Table(name="files")
@NamedQuery(name="File.findAll", query="SELECT f FROM File f")
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String url;

	//bi-directional many-to-one association to ServiceAttendace
	@ManyToOne
	@JoinColumn(name="service_attendace_id")
	@JsonBackReference
	private ServiceAttendace serviceAttendace;

	public File() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ServiceAttendace getServiceAttendace() {
		return this.serviceAttendace;
	}

	public void setServiceAttendace(ServiceAttendace serviceAttendace) {
		this.serviceAttendace = serviceAttendace;
	}

}