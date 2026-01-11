package com.edumanager.repository;

import com.edumanager.model.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour l'entité Programme
 */
@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
    List<Programme> findByCentreId(Long centreId);
}
