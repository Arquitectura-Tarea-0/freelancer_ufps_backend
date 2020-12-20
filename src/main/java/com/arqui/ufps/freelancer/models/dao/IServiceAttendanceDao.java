package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.ServiceAttendace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceAttendanceDao extends JpaRepository<ServiceAttendace, Integer> {
}
