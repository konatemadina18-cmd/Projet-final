package com.edumanager.controller;

import com.edumanager.model.*;
import com.edumanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller pour les pages d'administration
 */
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    
    @Autowired
    private InscriptionService inscriptionService;
    
    @Autowired
    private PaiementService paiementService;
    
    @Autowired
    private PresenceService presenceService;
    
    @Autowired
    private ApprenantService apprenantService;
    
    @Autowired
    private ProgrammeService programmeService;
    
    @Autowired
    private CohorteService cohorteService;
    
    @Autowired
    private CentreService centreService;
    
    @Autowired
    private EvaluationService evaluationService;
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Inscription> inscriptions = inscriptionService.findAll();
        BigDecimal totalRevenus = paiementService.getTotalRevenus();
        List<Inscription> dernieresInscriptions = inscriptions.stream()
                .sorted((a, b) -> b.getDateInscription().compareTo(a.getDateInscription()))
                .limit(10)
                .toList();
        
        long totalPresences = presenceService.findAll().stream()
                .filter(p -> p.getStatut() == Presence.StatutPresence.PRESENT)
                .count();
        long totalAbsences = presenceService.findAll().stream()
                .filter(p -> p.getStatut() == Presence.StatutPresence.ABSENT)
                .count();
        double tauxPresence = (totalPresences + totalAbsences) > 0 
                ? (double) totalPresences / (totalPresences + totalAbsences) * 100 
                : 0;
        
        model.addAttribute("totalInscriptions", inscriptions.size());
        model.addAttribute("totalRevenus", totalRevenus);
        model.addAttribute("tauxPresence", String.format("%.2f", tauxPresence));
        model.addAttribute("dernieresInscriptions", dernieresInscriptions);
        return "admin/dashboard";
    }
    
    // Gestion des apprenants
    @GetMapping("/apprenants")
    public String apprenants(Model model) {
        model.addAttribute("apprenants", apprenantService.findAll());
        return "admin/apprenants";
    }
    
    @GetMapping("/apprenants/new")
    public String newApprenant(Model model) {
        model.addAttribute("apprenant", new Apprenant());
        return "admin/apprenant-form";
    }
    
    @PostMapping("/apprenants")
    public String saveApprenant(@ModelAttribute Apprenant apprenant, RedirectAttributes redirectAttributes) {
        apprenantService.save(apprenant);
        redirectAttributes.addFlashAttribute("success", "Apprenant enregistré avec succès");
        return "redirect:/admin/apprenants";
    }
    
    @GetMapping("/apprenants/edit/{id}")
    public String editApprenant(@PathVariable Long id, Model model) {
        model.addAttribute("apprenant", apprenantService.findById(id).orElse(new Apprenant()));
        return "admin/apprenant-form";
    }
    
    @PostMapping("/apprenants/{id}")
    public String updateApprenant(@PathVariable Long id, @ModelAttribute Apprenant apprenant, RedirectAttributes redirectAttributes) {
        apprenantService.update(id, apprenant);
        redirectAttributes.addFlashAttribute("success", "Apprenant mis à jour avec succès");
        return "redirect:/admin/apprenants";
    }
    
    @GetMapping("/apprenants/delete/{id}")
    public String deleteApprenant(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        apprenantService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Apprenant supprimé avec succès");
        return "redirect:/admin/apprenants";
    }
    
    // Gestion des inscriptions
    @GetMapping("/inscriptions")
    public String inscriptions(Model model) {
        model.addAttribute("inscriptions", inscriptionService.findAll());
        return "admin/inscriptions";
    }
    
    @GetMapping("/inscriptions/edit/{id}")
    public String editInscription(@PathVariable Long id, Model model) {
        model.addAttribute("inscription", inscriptionService.findById(id).orElse(new Inscription()));
        model.addAttribute("apprenants", apprenantService.findAll());
        model.addAttribute("cohortes", cohorteService.findAll());
        return "admin/inscription-form";
    }
    
    @PostMapping("/inscriptions/{id}")
    public String updateInscription(@PathVariable Long id, @ModelAttribute Inscription inscription, RedirectAttributes redirectAttributes) {
        inscriptionService.update(id, inscription);
        redirectAttributes.addFlashAttribute("success", "Inscription mise à jour avec succès");
        return "redirect:/admin/inscriptions";
    }
    
    // Gestion des cohortes
    @GetMapping("/cohortes")
    public String cohortes(Model model) {
        model.addAttribute("cohortes", cohorteService.findAll());
        return "admin/cohortes";
    }
    
    @GetMapping("/cohortes/new")
    public String newCohorte(Model model) {
        model.addAttribute("cohorte", new Cohorte());
        model.addAttribute("programmes", programmeService.findAll());
        return "admin/cohorte-form";
    }
    
    @PostMapping("/cohortes")
    public String saveCohorte(@ModelAttribute Cohorte cohorte, RedirectAttributes redirectAttributes) {
        cohorteService.save(cohorte);
        redirectAttributes.addFlashAttribute("success", "Cohorte enregistrée avec succès");
        return "redirect:/admin/cohortes";
    }
    
    // Gestion des programmes
    @GetMapping("/programmes")
    public String programmes(Model model) {
        model.addAttribute("programmes", programmeService.findAll());
        return "admin/programmes";
    }
    
    @GetMapping("/programmes/new")
    public String newProgramme(Model model) {
        model.addAttribute("programme", new Programme());
        model.addAttribute("centres", centreService.findAll());
        return "admin/programme-form";
    }
    
    @PostMapping("/programmes")
    public String saveProgramme(@ModelAttribute Programme programme, RedirectAttributes redirectAttributes) {
        programmeService.save(programme);
        redirectAttributes.addFlashAttribute("success", "Programme enregistré avec succès");
        return "redirect:/admin/programmes";
    }
    
    // Gestion des paiements
    @GetMapping("/paiements")
    public String paiements(Model model) {
        model.addAttribute("paiements", paiementService.findAll());
        return "admin/paiements";
    }
    
    @GetMapping("/paiements/new")
    public String newPaiement(Model model) {
        model.addAttribute("paiement", new Paiement());
        model.addAttribute("inscriptions", inscriptionService.findAll());
        return "admin/paiement-form";
    }
    
    @PostMapping("/paiements")
    public String savePaiement(@ModelAttribute Paiement paiement, RedirectAttributes redirectAttributes) {
        paiementService.save(paiement);
        redirectAttributes.addFlashAttribute("success", "Paiement enregistré avec succès");
        return "redirect:/admin/paiements";
    }
    
    // Gestion des présences
    @GetMapping("/presences")
    public String presences(Model model) {
        model.addAttribute("presences", presenceService.findAll());
        return "admin/presences";
    }
    
    @GetMapping("/presences/new")
    public String newPresence(Model model) {
        model.addAttribute("presence", new Presence());
        model.addAttribute("inscriptions", inscriptionService.findAll());
        return "admin/presence-form";
    }
    
    @PostMapping("/presences")
    public String savePresence(@ModelAttribute Presence presence, RedirectAttributes redirectAttributes) {
        presenceService.save(presence);
        redirectAttributes.addFlashAttribute("success", "Presence enregistrée avec succès");
        return "redirect:/admin/presences";
    }
    
    // Gestion des évaluations
    @GetMapping("/evaluations")
    public String evaluations(Model model) {
        model.addAttribute("evaluations", evaluationService.findAll());
        return "admin/evaluations";
    }
    
    @GetMapping("/evaluations/new")
    public String newEvaluation(Model model) {
        model.addAttribute("evaluation", new Evaluation());
        model.addAttribute("inscriptions", inscriptionService.findAll());
        return "admin/evaluation-form";
    }
    
    @PostMapping("/evaluations")
    public String saveEvaluation(@ModelAttribute Evaluation evaluation, RedirectAttributes redirectAttributes) {
        evaluationService.save(evaluation);
        redirectAttributes.addFlashAttribute("success", "Evaluation enregistrée avec succès");
        return "redirect:/admin/evaluations";
    }
}
