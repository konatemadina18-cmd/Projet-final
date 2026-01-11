package com.edumanager.repository;

import com.edumanager.model.Formateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Formateur
 */
@Repository
public interface FormateurRepository extends JpaRepository<Formateur, Long> {
    List<Formateur> findByCentreId(Long centreId);
}
