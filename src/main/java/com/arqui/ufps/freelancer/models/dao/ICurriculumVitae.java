package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurriculumVitae extends JpaRepository<CurriculumVitae, Integer> {
}
