package com.arqui.ufps.freelancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.arqui.ufps.freelancer.model.entities.User;
import com.arqui.ufps.freelancer.repository.services.IUserService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH,RequestMethod.PUT})
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

