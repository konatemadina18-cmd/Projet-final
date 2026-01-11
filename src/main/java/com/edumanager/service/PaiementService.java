package com.edumanager.service;

import com.edumanager.model.Paiement;
import com.edumanager.repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des paiements
 */
@Service
@Transactional
public class PaiementService {
    
    @Autowired
    private PaiementRepository paiementRepository;
    
    public List<Paiement> findAll() {
        return paiementRepository.findAll();
    }
    
    public Optional<Paiement> findById(Long id) {
        return paiementRepository.findById(id);
    }
    
    public List<Paiement> findByInscriptionId(Long inscriptionId) {
        return paiementRepository.findByInscriptionId(inscriptionId);
    }
    
    public Paiement save(Paiement paiement) {
        return paiementRepository.save(paiement);
    }
    
    public Paiement update(Long id, Paiement paiement) {
        Paiement existing = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        existing.setInscription(paiement.getInscription());
        existing.setMontant(paiement.getMontant());
        existing.setDatePaiement(paiement.getDatePaiement());
        existing.setMode(paiement.getMode());
        return paiementRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        paiementRepository.deleteById(id);
    }
    
    public BigDecimal getTotalRevenus() {
        return paiementRepository.findAll().stream()
                .map(Paiement::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
