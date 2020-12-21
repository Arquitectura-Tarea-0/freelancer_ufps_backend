package com.arqui.ufps.freelancer.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the educations database table.
 * 
 */
@Entity
@Table(name="educations")
@NamedQuery(name="Education.findAll", query="SELECT e FROM Education e")
public class Education implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="institution_name")
	private String institutionName;

	//bi-directional many-to-one association to StudentEducation
	@OneToMany(mappedBy="education")
	@JsonManagedReference(value = "studentEducations")
	private List<StudentEducation> studentEducations;

	public Education() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getInstitutionName() {
		return this.institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public List<StudentEducation> getStudentEducations() {
		return this.studentEducations;
	}

	public void setStudentEducations(List<StudentEducation> studentEducations) {
		this.studentEducations = studentEducations;
	}

	public StudentEducation addStudentEducation(StudentEducation studentEducation) {
		getStudentEducations().add(studentEducation);
		studentEducation.setEducation(this);

		return studentEducation;
	}

	public StudentEducation removeStudentEducation(StudentEducation studentEducation) {
		getStudentEducations().remove(studentEducation);
		studentEducation.setEducation(null);

		return studentEducation;
	}

}