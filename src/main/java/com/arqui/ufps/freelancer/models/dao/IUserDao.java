package com.arqui.ufps.freelancer.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.ufps.freelancer.models.entities.User;

public interface IUserDao extends JpaRepository<User, Integer>{
    @Query("select u from User u where u.email=?1")
	public User findByEmail(String email);
}
