package com.arqui.ufps.freelancer.models.dao;

import com.arqui.ufps.freelancer.models.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileDao extends JpaRepository<File, Integer> {
}
