package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer> {
}
