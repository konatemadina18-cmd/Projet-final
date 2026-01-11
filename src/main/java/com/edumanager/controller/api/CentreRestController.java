package com.edumanager.controller.api;

import com.edumanager.model.Centre;
import com.edumanager.service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des centres
 */
@RestController
@RequestMapping("/api/centres")
@CrossOrigin(origins = "*")
public class CentreRestController {
    
    @Autowired
    private CentreService centreService;
    
    @GetMapping
    public ResponseEntity<List<Centre>> getAllCentres() {
        return ResponseEntity.ok(centreService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Centre> getCentreById(@PathVariable Long id) {
        return centreService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Centre> createCentre(@RequestBody Centre centre) {
        return ResponseEntity.status(HttpStatus.CREATED).body(centreService.save(centre));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Centre> updateCentre(@PathVariable Long id, @RequestBody Centre centre) {
        try {
            return ResponseEntity.ok(centreService.update(id, centre));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
        centreService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
