package com.edumanager.repository;

import com.edumanager.model.Presence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Presence
 */
@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    List<Presence> findByInscriptionId(Long inscriptionId);
}
