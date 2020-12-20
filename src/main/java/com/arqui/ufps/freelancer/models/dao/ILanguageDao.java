package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILanguageDao extends JpaRepository<Language, Integer> {
}
