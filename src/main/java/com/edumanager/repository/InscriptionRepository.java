package com.edumanager.repository;

import com.edumanager.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Inscription
 */
@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Long> {
    List<Inscription> findByApprenantId(Long apprenantId);
    List<Inscription> findByCohorteId(Long cohorteId);
    List<Inscription> findByStatut(Inscription.StatutInscription statut);
}
