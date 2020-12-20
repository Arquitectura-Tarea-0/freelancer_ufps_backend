package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.StudentEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentEducationDao extends JpaRepository<StudentEducation, Integer> {
}
