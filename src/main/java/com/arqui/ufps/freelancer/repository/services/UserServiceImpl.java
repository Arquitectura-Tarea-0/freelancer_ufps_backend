package com.arqui.ufps.freelancer.repository.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arqui.ufps.freelancer.repository.dao.IUserDao;
import com.arqui.ufps.freelancer.model.entities.User;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	
	@Override
	@Transactional
	public User save(User usuario) {
		// TODO Auto-generated method stub
		return userDao.save(usuario);
	}

	@Override
	@Transactional
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.findByEmail(email);
	}

	@Override
	@Transactional
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}
	

}
