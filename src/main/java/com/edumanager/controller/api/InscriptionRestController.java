package com.edumanager.controller.api;

import com.edumanager.model.Inscription;
import com.edumanager.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des inscriptions
 */
@RestController
@RequestMapping("/api/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionRestController {
    
    @Autowired
    private InscriptionService inscriptionService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<Inscription>> getAllInscriptions() {
        return ResponseEntity.ok(inscriptionService.findAll());
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Inscription> getInscriptionById(@PathVariable Long id) {
        return inscriptionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/apprenant/{apprenantId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<Inscription>> getInscriptionsByApprenant(@PathVariable Long apprenantId) {
        return ResponseEntity.ok(inscriptionService.findByApprenantId(apprenantId));
    }
    
    @GetMapping("/cohorte/{cohorteId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<Inscription>> getInscriptionsByCohorte(@PathVariable Long cohorteId) {
        return ResponseEntity.ok(inscriptionService.findByCohorteId(cohorteId));
    }
    
    @PostMapping
    public ResponseEntity<Inscription> createInscription(@RequestBody Inscription inscription) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inscriptionService.save(inscription));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Inscription> updateInscription(@PathVariable Long id, @RequestBody Inscription inscription) {
        try {
            return ResponseEntity.ok(inscriptionService.update(id, inscription));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
