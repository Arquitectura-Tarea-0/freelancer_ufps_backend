package com.arqui.ufps.freelancer.models.services;

import java.util.List;

import com.arqui.ufps.freelancer.models.entities.User;

public interface IUserService{
	
	public User save(User usuario);
//	@Query("select u from User u where u.email='?1'")
	public User findByEmail(String email);
	public List<User> findAll();
}
