package com.arqui.ufps.freelancer.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.ufps.freelancer.model.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Integer>{
    @Query(value="select * from users u where u.email=?1",nativeQuery = true)
	public User findByEmail(String email);
    
    public User findById(int id);

    @Query(value="select * from users u where u.id_user=?1",nativeQuery = true)
    public User findUserById(Integer id);
}
