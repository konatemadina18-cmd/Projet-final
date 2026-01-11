package com.edumanager.service;

import com.edumanager.model.Presence;
import com.edumanager.repository.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des présences
 */
@Service
@Transactional
public class PresenceService {
    
    @Autowired
    private PresenceRepository presenceRepository;
    
    public List<Presence> findAll() {
        return presenceRepository.findAll();
    }
    
    public Optional<Presence> findById(Long id) {
        return presenceRepository.findById(id);
    }
    
    public List<Presence> findByInscriptionId(Long inscriptionId) {
        return presenceRepository.findByInscriptionId(inscriptionId);
    }
    
    public Presence save(Presence presence) {
        return presenceRepository.save(presence);
    }
    
    public Presence update(Long id, Presence presence) {
        Presence existing = presenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Presence non trouvée"));
        existing.setInscription(presence.getInscription());
        existing.setDate(presence.getDate());
        existing.setStatut(presence.getStatut());
        return presenceRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        presenceRepository.deleteById(id);
    }
}
