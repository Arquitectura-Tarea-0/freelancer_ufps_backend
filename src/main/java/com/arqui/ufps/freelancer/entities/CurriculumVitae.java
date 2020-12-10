package com.arqui.ufps.freelancer.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the curriculum_vitae database table.
 * 
 */
@Entity
@Table(name="curriculum_vitae")
@NamedQuery(name="CurriculumVitae.findAll", query="SELECT c FROM CurriculumVitae c")
public class CurriculumVitae implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Lob
	private String description;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-one association to Certificate
	@OneToMany(mappedBy="curriculumVitae")
	private List<Certificate> certificates;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	//bi-directional many-to-one association to Language
	@OneToMany(mappedBy="curriculumVitae")
	private List<Language> languages;

	//bi-directional many-to-one association to StudentEducation
	@OneToMany(mappedBy="curriculumVitae")
	private List<StudentEducation> studentEducations;

	//bi-directional many-to-one association to StudentSkill
	@OneToMany(mappedBy="curriculumVitae")
	private List<StudentSkill> studentSkills;

	public CurriculumVitae() {
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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Certificate> getCertificates() {
		return this.certificates;
	}

	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}

	public Certificate addCertificate(Certificate certificate) {
		getCertificates().add(certificate);
		certificate.setCurriculumVitae(this);

		return certificate;
	}

	public Certificate removeCertificate(Certificate certificate) {
		getCertificates().remove(certificate);
		certificate.setCurriculumVitae(null);

		return certificate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Language> getLanguages() {
		return this.languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
	}

	public Language addLanguage(Language language) {
		getLanguages().add(language);
		language.setCurriculumVitae(this);

		return language;
	}

	public Language removeLanguage(Language language) {
		getLanguages().remove(language);
		language.setCurriculumVitae(null);

		return language;
	}

	public List<StudentEducation> getStudentEducations() {
		return this.studentEducations;
	}

	public void setStudentEducations(List<StudentEducation> studentEducations) {
		this.studentEducations = studentEducations;
	}

	public StudentEducation addStudentEducation(StudentEducation studentEducation) {
		getStudentEducations().add(studentEducation);
		studentEducation.setCurriculumVitae(this);

		return studentEducation;
	}

	public StudentEducation removeStudentEducation(StudentEducation studentEducation) {
		getStudentEducations().remove(studentEducation);
		studentEducation.setCurriculumVitae(null);

		return studentEducation;
	}

	public List<StudentSkill> getStudentSkills() {
		return this.studentSkills;
	}

	public void setStudentSkills(List<StudentSkill> studentSkills) {
		this.studentSkills = studentSkills;
	}

	public StudentSkill addStudentSkill(StudentSkill studentSkill) {
		getStudentSkills().add(studentSkill);
		studentSkill.setCurriculumVitae(this);

		return studentSkill;
	}

	public StudentSkill removeStudentSkill(StudentSkill studentSkill) {
		getStudentSkills().remove(studentSkill);
		studentSkill.setCurriculumVitae(null);

		return studentSkill;
	}

}