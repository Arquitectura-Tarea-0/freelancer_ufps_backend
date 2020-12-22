package com.arqui.ufps.freelancer.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.ufps.freelancer.model.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
    @Query("select u from User u where u.email=?1")
	public User findByEmail(String email);
    
    public User findById(int id);
}
