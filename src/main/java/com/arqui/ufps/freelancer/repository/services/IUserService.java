package com.arqui.ufps.freelancer.repository.services;

import java.util.List;

import com.arqui.ufps.freelancer.model.entities.User;

public interface IUserService{
	
	public User save(User usuario);
//	@Query("select u from User u where u.email='?1'")
	public User findByEmail(String email);
	public List<User> findAll();
}
