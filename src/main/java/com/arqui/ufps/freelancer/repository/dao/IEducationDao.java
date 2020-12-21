package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationDao extends JpaRepository<Education, Integer> {

    @Query(value = "select * from educations where institution_name = ?1", nativeQuery = true)
    public Education findByName(String institutionName);

}
