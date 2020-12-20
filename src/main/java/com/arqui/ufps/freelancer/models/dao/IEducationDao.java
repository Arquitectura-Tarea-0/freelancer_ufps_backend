package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationDao extends JpaRepository<Education, Integer> {
}
