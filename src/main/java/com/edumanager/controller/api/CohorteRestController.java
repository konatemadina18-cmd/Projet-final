package com.edumanager.controller.api;

import com.edumanager.model.Cohorte;
import com.edumanager.service.CohorteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des cohortes
 */
@RestController
@RequestMapping("/api/cohortes")
@CrossOrigin(origins = "*")
public class CohorteRestController {
    
    @Autowired
    private CohorteService cohorteService;
    
    @GetMapping
    public ResponseEntity<List<Cohorte>> getAllCohortes() {
        return ResponseEntity.ok(cohorteService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cohorte> getCohorteById(@PathVariable Long id) {
        return cohorteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/programme/{programmeId}")
    public ResponseEntity<List<Cohorte>> getCohortesByProgramme(@PathVariable Long programmeId) {
        return ResponseEntity.ok(cohorteService.findByProgrammeId(programmeId));
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Cohorte> createCohorte(@RequestBody Cohorte cohorte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cohorteService.save(cohorte));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Cohorte> updateCohorte(@PathVariable Long id, @RequestBody Cohorte cohorte) {
        try {
            return ResponseEntity.ok(cohorteService.update(id, cohorte));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCohorte(@PathVariable Long id) {
        cohorteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
