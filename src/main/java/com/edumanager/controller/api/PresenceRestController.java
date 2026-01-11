package com.edumanager.controller.api;

import com.edumanager.model.Presence;
import com.edumanager.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des présences
 */
@RestController
@RequestMapping("/api/presences")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
public class PresenceRestController {
    
    @Autowired
    private PresenceService presenceService;
    
    @GetMapping
    public ResponseEntity<List<Presence>> getAllPresences() {
        return ResponseEntity.ok(presenceService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Presence> getPresenceById(@PathVariable Long id) {
        return presenceService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/inscription/{inscriptionId}")
    public ResponseEntity<List<Presence>> getPresencesByInscription(@PathVariable Long inscriptionId) {
        return ResponseEntity.ok(presenceService.findByInscriptionId(inscriptionId));
    }
    
    @PostMapping
    public ResponseEntity<Presence> createPresence(@RequestBody Presence presence) {
        return ResponseEntity.status(HttpStatus.CREATED).body(presenceService.save(presence));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Presence> updatePresence(@PathVariable Long id, @RequestBody Presence presence) {
        try {
            return ResponseEntity.ok(presenceService.update(id, presence));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePresence(@PathVariable Long id) {
        presenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
