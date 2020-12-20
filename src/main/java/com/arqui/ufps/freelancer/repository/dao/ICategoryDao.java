package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.Category;
import com.arqui.ufps.freelancer.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Integer> {
    @Query(value = "select * from categories where id = ?1", nativeQuery = true)
    public Category findById(int id);

    @Query(value = "select * from categories where name = ?1", nativeQuery = true)
    public Category findByName(String name);
}
