package com.edumanager.service;

import com.edumanager.model.Inscription;
import com.edumanager.repository.InscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des inscriptions
 */
@Service
@Transactional
public class InscriptionService {
    
    @Autowired
    private InscriptionRepository inscriptionRepository;
    
    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }
    
    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }
    
    public List<Inscription> findByApprenantId(Long apprenantId) {
        return inscriptionRepository.findByApprenantId(apprenantId);
    }
    
    public List<Inscription> findByCohorteId(Long cohorteId) {
        return inscriptionRepository.findByCohorteId(cohorteId);
    }
    
    public List<Inscription> findByStatut(Inscription.StatutInscription statut) {
        return inscriptionRepository.findByStatut(statut);
    }
    
    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }
    
    public Inscription update(Long id, Inscription inscription) {
        Inscription existing = inscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription non trouvée"));
        existing.setApprenant(inscription.getApprenant());
        existing.setCohorte(inscription.getCohorte());
        existing.setDateInscription(inscription.getDateInscription());
        existing.setStatut(inscription.getStatut());
        return inscriptionRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }
}
