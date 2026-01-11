package com.edumanager.service;

import com.edumanager.model.Formateur;
import com.edumanager.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des formateurs
 */
@Service
@Transactional
public class FormateurService {
    
    @Autowired
    private FormateurRepository formateurRepository;
    
    public List<Formateur> findAll() {
        return formateurRepository.findAll();
    }
    
    public Optional<Formateur> findById(Long id) {
        return formateurRepository.findById(id);
    }
    
    public List<Formateur> findByCentreId(Long centreId) {
        return formateurRepository.findByCentreId(centreId);
    }
    
    public Formateur save(Formateur formateur) {
        return formateurRepository.save(formateur);
    }
    
    public Formateur update(Long id, Formateur formateur) {
        Formateur existing = formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur non trouvé"));
        existing.setNom(formateur.getNom());
        existing.setSpecialite(formateur.getSpecialite());
        existing.setCentre(formateur.getCentre());
        existing.setEmail(formateur.getEmail());
        existing.setTelephone(formateur.getTelephone());
        return formateurRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        formateurRepository.deleteById(id);
    }
}
