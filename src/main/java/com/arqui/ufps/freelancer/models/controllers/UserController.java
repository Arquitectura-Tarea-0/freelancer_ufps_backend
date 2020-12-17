package com.arqui.ufps.freelancer.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arqui.ufps.freelancer.models.entities.User;
import com.arqui.ufps.freelancer.models.services.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/user/listar")
	public List<User> list(){
		return userService.findAll();
	}
	
	@PostMapping({"/signup", "/"})
	public User save(@RequestBody User user) {
		String bcryptPassword = passwordEncoder.encode(user.getPassword());
		return userService.save(new User(user.getCreatedAt(),user.getEmail(),bcryptPassword,user.getPhone(),user.getRole()));
	}
}

