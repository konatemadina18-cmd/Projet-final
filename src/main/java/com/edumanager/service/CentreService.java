package com.edumanager.service;

import com.edumanager.model.Centre;
import com.edumanager.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des centres
 */
@Service
@Transactional
public class CentreService {
    
    @Autowired
    private CentreRepository centreRepository;
    
    public List<Centre> findAll() {
        return centreRepository.findAll();
    }
    
    public Optional<Centre> findById(Long id) {
        return centreRepository.findById(id);
    }
    
    public Centre save(Centre centre) {
        return centreRepository.save(centre);
    }
    
    public Centre update(Long id, Centre centre) {
        Centre existing = centreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Centre non trouvé"));
        existing.setNom(centre.getNom());
        existing.setLocalisation(centre.getLocalisation());
        return centreRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        centreRepository.deleteById(id);
    }
}
