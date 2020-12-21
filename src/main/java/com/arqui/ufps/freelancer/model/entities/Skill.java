package com.arqui.ufps.freelancer.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skills database table.
 * 
 */
@Entity
@Table(name="skills")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	//bi-directional many-to-one association to StudentSkill
	@OneToMany(mappedBy="skill")
	@JsonManagedReference(value = "studentSkills")
	private List<StudentSkill> studentSkills;

	public Skill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StudentSkill> getStudentSkills() {
		return this.studentSkills;
	}

	public void setStudentSkills(List<StudentSkill> studentSkills) {
		this.studentSkills = studentSkills;
	}

	public StudentSkill addStudentSkill(StudentSkill studentSkill) {
		getStudentSkills().add(studentSkill);
		studentSkill.setSkill(this);

		return studentSkill;
	}

	public StudentSkill removeStudentSkill(StudentSkill studentSkill) {
		getStudentSkills().remove(studentSkill);
		studentSkill.setSkill(null);

		return studentSkill;
	}

}