package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificateDao extends JpaRepository<Certificate, Integer> {
}
