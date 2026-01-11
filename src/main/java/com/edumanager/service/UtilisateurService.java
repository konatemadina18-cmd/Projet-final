package com.edumanager.service;

import com.edumanager.model.Utilisateur;
import com.edumanager.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des utilisateurs
 */
@Service
@Transactional
public class UtilisateurService {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }
    
    public Optional<Utilisateur> findById(Long id) {
        return utilisateurRepository.findById(id);
    }
    
    public Optional<Utilisateur> findByUsername(String username) {
        return utilisateurRepository.findByUsername(username);
    }
    
    public Utilisateur save(Utilisateur utilisateur) {
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        return utilisateurRepository.save(utilisateur);
    }
    
    public Utilisateur update(Long id, Utilisateur utilisateur) {
        Utilisateur existing = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        existing.setUsername(utilisateur.getUsername());
        if (utilisateur.getPassword() != null && !utilisateur.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        }
        existing.setRole(utilisateur.getRole());
        return utilisateurRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }
    
    public boolean existsByUsername(String username) {
        return utilisateurRepository.existsByUsername(username);
    }
}
