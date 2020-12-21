package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.Category;
import com.arqui.ufps.freelancer.model.entities.CurriculumVitae;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurriculumVitaeDao extends JpaRepository<CurriculumVitae, Integer> {

    @Query(value = "select * from curriculum_vitae where id = ?1", nativeQuery = true)
    public CurriculumVitae findById(int id);

}
