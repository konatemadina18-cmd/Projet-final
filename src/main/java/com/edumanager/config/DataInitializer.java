package com.edumanager.config;

import com.edumanager.model.Centre;
import com.edumanager.model.Utilisateur;
import com.edumanager.repository.CentreRepository;
import com.edumanager.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Initialisation des données par défaut
 */
@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    
    @Autowired
    private CentreRepository centreRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public void run(String... args) throws Exception {
        // Créer un utilisateur admin par défaut
        if (utilisateurRepository.findByUsername("admin").isEmpty()) {
            Utilisateur admin = new Utilisateur();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Utilisateur.Role.ADMIN);
            utilisateurRepository.save(admin);
        }
        
        // Créer un utilisateur staff par défaut
        if (utilisateurRepository.findByUsername("staff").isEmpty()) {
            Utilisateur staff = new Utilisateur();
            staff.setUsername("staff");
            staff.setPassword(passwordEncoder.encode("staff123"));
            staff.setRole(Utilisateur.Role.STAFF);
            utilisateurRepository.save(staff);
        }
        
        // Créer un centre par défaut
        if (centreRepository.count() == 0) {
            Centre centre = new Centre();
            centre.setNom("Centre Principal");
            centre.setLocalisation("Ville, Pays");
            centreRepository.save(centre);
        }
    }
}
