package com.edumanager.controller.api;

import com.edumanager.model.Programme;
import com.edumanager.service.ProgrammeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des programmes
 */
@RestController
@RequestMapping("/api/programmes")
@CrossOrigin(origins = "*")
public class ProgrammeRestController {
    
    @Autowired
    private ProgrammeService programmeService;
    
    @GetMapping
    public ResponseEntity<List<Programme>> getAllProgrammes() {
        return ResponseEntity.ok(programmeService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Programme> getProgrammeById(@PathVariable Long id) {
        return programmeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/centre/{centreId}")
    public ResponseEntity<List<Programme>> getProgrammesByCentre(@PathVariable Long centreId) {
        return ResponseEntity.ok(programmeService.findByCentreId(centreId));
    }
    
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Programme> createProgramme(@RequestBody Programme programme) {
        return ResponseEntity.status(HttpStatus.CREATED).body(programmeService.save(programme));
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseEntity<Programme> updateProgramme(@PathVariable Long id, @RequestBody Programme programme) {
        try {
            return ResponseEntity.ok(programmeService.update(id, programme));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProgramme(@PathVariable Long id) {
        programmeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
