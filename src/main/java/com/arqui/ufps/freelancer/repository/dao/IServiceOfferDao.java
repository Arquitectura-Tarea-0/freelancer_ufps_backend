package com.arqui.ufps.freelancer.repository.dao;

import com.arqui.ufps.freelancer.model.entities.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IServiceOfferDao extends JpaRepository<ServiceOffer, Integer> {
    @Query(value = "select * from service_offers where user_id = ?1", nativeQuery = true)
    public List<ServiceOffer> findByUser(int userId);

    @Query(value = "select * from service_offers where id = ?1", nativeQuery = true)
    public ServiceOffer findById(int id);
}
