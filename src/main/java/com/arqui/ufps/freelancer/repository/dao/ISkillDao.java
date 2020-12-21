package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkillDao extends JpaRepository<Skill, Integer> {

    @Query(value = "select * from skills where name = ?1", nativeQuery = true)
    public Skill findByName(String name);

}
