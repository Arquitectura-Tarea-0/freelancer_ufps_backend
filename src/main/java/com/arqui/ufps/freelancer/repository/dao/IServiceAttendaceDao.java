package com.arqui.ufps.freelancer.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arqui.ufps.freelancer.model.entities.ServiceAttendace;

public interface IServiceAttendaceDao extends JpaRepository<ServiceAttendace,Integer> {
	
	
	@Query(value = "select * from service_attendaces st where st.id= ?1 ", nativeQuery = true)
    public ServiceAttendace findByIddolo(int attendaceId);
}
