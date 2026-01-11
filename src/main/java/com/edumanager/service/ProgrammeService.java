package com.edumanager.service;

import com.edumanager.model.Programme;
import com.edumanager.repository.ProgrammeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des programmes
 */
@Service
@Transactional
public class ProgrammeService {
    
    @Autowired
    private ProgrammeRepository programmeRepository;
    
    public List<Programme> findAll() {
        return programmeRepository.findAll();
    }
    
    public Optional<Programme> findById(Long id) {
        return programmeRepository.findById(id);
    }
    
    public List<Programme> findByCentreId(Long centreId) {
        return programmeRepository.findByCentreId(centreId);
    }
    
    public Programme save(Programme programme) {
        return programmeRepository.save(programme);
    }
    
    public Programme update(Long id, Programme programme) {
        Programme existing = programmeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programme non trouvé"));
        existing.setNom(programme.getNom());
        existing.setDescription(programme.getDescription());
        existing.setDuree(programme.getDuree());
        existing.setCentre(programme.getCentre());
        return programmeRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        programmeRepository.deleteById(id);
    }
}
