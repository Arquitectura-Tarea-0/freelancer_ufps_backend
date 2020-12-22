package com.arqui.ufps.freelancer.repository.dao;


import com.arqui.ufps.freelancer.model.entities.ServiceRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceRequestDao extends JpaRepository<ServiceRequest, Integer> {
	
	@Query(value = "select * from service_request where user_id = ?1 and state='ACTIVE' ", nativeQuery = true)
    public List<ServiceRequest> findByUser(int userId);
	
	
}
