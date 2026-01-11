package com.edumanager.service;

import com.edumanager.model.Apprenant;
import com.edumanager.repository.ApprenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des apprenants
 */
@Service
@Transactional
public class ApprenantService {
    
    @Autowired
    private ApprenantRepository apprenantRepository;
    
    public List<Apprenant> findAll() {
        return apprenantRepository.findAll();
    }
    
    public Optional<Apprenant> findById(Long id) {
        return apprenantRepository.findById(id);
    }
    
    public Optional<Apprenant> findByEmail(String email) {
        return apprenantRepository.findByEmail(email);
    }
    
    public Apprenant save(Apprenant apprenant) {
        return apprenantRepository.save(apprenant);
    }
    
    public Apprenant update(Long id, Apprenant apprenant) {
        Apprenant existing = apprenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apprenant non trouvé"));
        existing.setNom(apprenant.getNom());
        existing.setPrenom(apprenant.getPrenom());
        existing.setTelephone(apprenant.getTelephone());
        existing.setEmail(apprenant.getEmail());
        return apprenantRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        apprenantRepository.deleteById(id);
    }
    
    public boolean existsByEmail(String email) {
        return apprenantRepository.existsByEmail(email);
    }
}
