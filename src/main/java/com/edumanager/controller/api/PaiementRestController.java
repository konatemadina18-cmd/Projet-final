package com.edumanager.controller.api;

import com.edumanager.model.Paiement;
import com.edumanager.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des paiements
 */
@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
public class PaiementRestController {
    
    @Autowired
    private PaiementService paiementService;
    
    @GetMapping
    public ResponseEntity<List<Paiement>> getAllPaiements() {
        return ResponseEntity.ok(paiementService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable Long id) {
        return paiementService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/inscription/{inscriptionId}")
    public ResponseEntity<List<Paiement>> getPaiementsByInscription(@PathVariable Long inscriptionId) {
        return ResponseEntity.ok(paiementService.findByInscriptionId(inscriptionId));
    }
    
    @PostMapping
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.save(paiement));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Paiement> updatePaiement(@PathVariable Long id, @RequestBody Paiement paiement) {
        try {
            return ResponseEntity.ok(paiementService.update(id, paiement));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
