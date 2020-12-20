package com.arqui.ufps.freelancer.model.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the student_skills database table.
 * 
 */
@Entity
@Table(name="student_skills")
@NamedQuery(name="StudentSkill.findAll", query="SELECT s FROM StudentSkill s")
public class StudentSkill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String level;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	private Skill skill;

	//bi-directional many-to-one association to CurriculumVitae
	@ManyToOne
	@JoinColumn(name="curriculum_vitae_id")
	private CurriculumVitae curriculumVitae;

	public StudentSkill() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public CurriculumVitae getCurriculumVitae() {
		return this.curriculumVitae;
	}

	public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
		this.curriculumVitae = curriculumVitae;
	}

}