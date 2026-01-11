package com.edumanager.controller.api;

import com.edumanager.model.Apprenant;
import com.edumanager.service.ApprenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des apprenants
 */
@RestController
@RequestMapping("/api/apprenants")
@CrossOrigin(origins = "*")
public class ApprenantRestController {
    
    @Autowired
    private ApprenantService apprenantService;
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<List<Apprenant>> getAllApprenants() {
        return ResponseEntity.ok(apprenantService.findAll());
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Apprenant> getApprenantById(@PathVariable Long id) {
        return apprenantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Apprenant> createApprenant(@RequestBody Apprenant apprenant) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apprenantService.save(apprenant));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Apprenant> updateApprenant(@PathVariable Long id, @RequestBody Apprenant apprenant) {
        try {
            return ResponseEntity.ok(apprenantService.update(id, apprenant));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteApprenant(@PathVariable Long id) {
        apprenantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
