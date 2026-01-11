package com.edumanager.repository;

import com.edumanager.model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository pour l'entité Centre
 */
@Repository
public interface CentreRepository extends JpaRepository<Centre, Long> {
}
