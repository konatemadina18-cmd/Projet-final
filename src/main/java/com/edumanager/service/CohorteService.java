package com.edumanager.service;

import com.edumanager.model.Cohorte;
import com.edumanager.repository.CohorteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des cohortes
 */
@Service
@Transactional
public class CohorteService {
    
    @Autowired
    private CohorteRepository cohorteRepository;
    
    public List<Cohorte> findAll() {
        return cohorteRepository.findAll();
    }
    
    public Optional<Cohorte> findById(Long id) {
        return cohorteRepository.findById(id);
    }
    
    public List<Cohorte> findByProgrammeId(Long programmeId) {
        return cohorteRepository.findByProgrammeId(programmeId);
    }
    
    public Cohorte save(Cohorte cohorte) {
        return cohorteRepository.save(cohorte);
    }
    
    public Cohorte update(Long id, Cohorte cohorte) {
        Cohorte existing = cohorteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cohorte non trouvée"));
        existing.setNom(cohorte.getNom());
        existing.setDateDebut(cohorte.getDateDebut());
        existing.setDateFin(cohorte.getDateFin());
        existing.setProgramme(cohorte.getProgramme());
        return cohorteRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        cohorteRepository.deleteById(id);
    }
}
