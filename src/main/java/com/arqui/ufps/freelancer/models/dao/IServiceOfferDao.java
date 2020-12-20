package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IServiceOfferDao extends JpaRepository<ServiceOffer, Integer> {
}
