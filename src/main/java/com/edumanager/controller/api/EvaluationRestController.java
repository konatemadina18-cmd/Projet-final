package com.edumanager.controller.api;

import com.edumanager.model.Evaluation;
import com.edumanager.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour la gestion des évaluations
 */
@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
public class EvaluationRestController {
    
    @Autowired
    private EvaluationService evaluationService;
    
    @GetMapping
    public ResponseEntity<List<Evaluation>> getAllEvaluations() {
        return ResponseEntity.ok(evaluationService.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        return evaluationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/inscription/{inscriptionId}")
    public ResponseEntity<List<Evaluation>> getEvaluationsByInscription(@PathVariable Long inscriptionId) {
        return ResponseEntity.ok(evaluationService.findByInscriptionId(inscriptionId));
    }
    
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(evaluationService.save(evaluation));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        try {
            return ResponseEntity.ok(evaluationService.update(id, evaluation));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
