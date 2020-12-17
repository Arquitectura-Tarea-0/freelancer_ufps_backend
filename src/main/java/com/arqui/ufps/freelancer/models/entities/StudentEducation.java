package com.arqui.ufps.freelancer.models.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the student_educations database table.
 * 
 */
@Entity
@Table(name="student_educations")
@NamedQuery(name="StudentEducation.findAll", query="SELECT s FROM StudentEducation s")
public class StudentEducation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="graduation_year")
	private Date graduationYear;

	@Column(name="institution_type")
	private String institutionType;

	private String major;

	//bi-directional many-to-one association to CurriculumVitae
	@ManyToOne
	@JoinColumn(name="curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	//bi-directional many-to-one association to Education
	@ManyToOne
	private Education education;

	public StudentEducation() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getGraduationYear() {
		return this.graduationYear;
	}

	public void setGraduationYear(Date graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getInstitutionType() {
		return this.institutionType;
	}

	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public CurriculumVitae getCurriculumVitae() {
		return this.curriculumVitae;
	}

	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}

	public Education getEducation() {
		return this.education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

}