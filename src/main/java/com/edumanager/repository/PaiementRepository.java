package com.edumanager.repository;

import com.edumanager.model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Paiement
 */
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByInscriptionId(Long inscriptionId);
}
