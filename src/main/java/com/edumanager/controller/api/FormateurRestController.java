package com.edumanager.controller.api;

import com.edumanager.model.Formateur;
import com.edumanager.service.FormateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des formateurs
 */
@RestController
@RequestMapping("/api/formateurs")
@CrossOrigin(origins = "*")
public class FormateurRestController {
    
    @Autowired
    private FormateurService formateurService;
    
    @GetMapping
    public ResponseEntity<List<Formateur>> getAllFormateurs() {
        return ResponseEntity.ok(formateurService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Formateur> getFormateurById(@PathVariable Long id) {
        return formateurService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/centre/{centreId}")
    public ResponseEntity<List<Formateur>> getFormateursByCentre(@PathVariable Long centreId) {
        return ResponseEntity.ok(formateurService.findByCentreId(centreId));
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Formateur> createFormateur(@RequestBody Formateur formateur) {
        return ResponseEntity.status(HttpStatus.CREATED).body(formateurService.save(formateur));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Formateur> updateFormateur(@PathVariable Long id, @RequestBody Formateur formateur) {
        try {
            return ResponseEntity.ok(formateurService.update(id, formateur));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteFormateur(@PathVariable Long id) {
        formateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
