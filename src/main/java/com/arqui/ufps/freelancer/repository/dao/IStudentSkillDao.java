package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.StudentSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentSkillDao extends JpaRepository<StudentSkill, Integer> {
}
