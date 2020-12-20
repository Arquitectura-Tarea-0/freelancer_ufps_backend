package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.StudentEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentEducationDao extends JpaRepository<StudentEducation, Integer> {
}
