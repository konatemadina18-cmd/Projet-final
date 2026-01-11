package com.edumanager.repository;

import com.edumanager.model.Cohorte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Cohorte
 */
@Repository
public interface CohorteRepository extends JpaRepository<Cohorte, Long> {
    List<Cohorte> findByProgrammeId(Long programmeId);
}
