package com.edumanager.controller;

import com.edumanager.model.Apprenant;
import com.edumanager.model.Cohorte;
import com.edumanager.model.Inscription;
import com.edumanager.service.ApprenantService;
import com.edumanager.service.CohorteService;
import com.edumanager.service.InscriptionService;
import com.edumanager.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Controller pour les pages publiques
 */
@Controller
public class PublicController {
    
    @Autowired
    private ProgrammeService programmeService;
    
    @Autowired
    private CohorteService cohorteService;
    
    @Autowired
    private ApprenantService apprenantService;
    
    @Autowired
    private InscriptionService inscriptionService;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("programmes", programmeService.findAll());
        return "index";
    }
    
    @GetMapping("/programmes")
    public String programmes(Model model) {
        model.addAttribute("programmes", programmeService.findAll());
        return "programmes";
    }
    
    @GetMapping("/inscription")
    public String inscriptionForm(Model model) {
        model.addAttribute("apprenant", new Apprenant());
        model.addAttribute("programmes", programmeService.findAll());
        model.addAttribute("cohortes", cohorteService.findAll());
        return "inscription";
    }
    
    @PostMapping("/inscription")
    public String submitInscription(
            @ModelAttribute Apprenant apprenant,
            @RequestParam Long cohorteId,
            Model model) {
        
        try {
            // Vérifier si l'apprenant existe déjà
            Apprenant existingApprenant = apprenantService.findByEmail(apprenant.getEmail())
                    .orElse(null);
            
            if (existingApprenant == null) {
                existingApprenant = apprenantService.save(apprenant);
            }
            
            Cohorte cohorte = cohorteService.findById(cohorteId)
                    .orElseThrow(() -> new RuntimeException("Cohorte non trouvée"));
            
            Inscription inscription = new Inscription();
            inscription.setApprenant(existingApprenant);
            inscription.setCohorte(cohorte);
            inscription.setDateInscription(LocalDate.now());
            inscription.setStatut(Inscription.StatutInscription.EN_ATTENTE);
            
            inscriptionService.save(inscription);
            
            model.addAttribute("success", true);
            model.addAttribute("message", "Votre inscription a été soumise avec succès!");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Une erreur est survenue: " + e.getMessage());
        }
        
        model.addAttribute("programmes", programmeService.findAll());
        model.addAttribute("cohortes", cohorteService.findAll());
        return "inscription";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
