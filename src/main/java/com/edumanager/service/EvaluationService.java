package com.edumanager.service;

import com.edumanager.model.Evaluation;
import com.edumanager.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service pour la gestion des évaluations
 */
@Service
@Transactional
public class EvaluationService {
    
    @Autowired
    private EvaluationRepository evaluationRepository;
    
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }
    
    public Optional<Evaluation> findById(Long id) {
        return evaluationRepository.findById(id);
    }
    
    public List<Evaluation> findByInscriptionId(Long inscriptionId) {
        return evaluationRepository.findByInscriptionId(inscriptionId);
    }
    
    public Evaluation save(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }
    
    public Evaluation update(Long id, Evaluation evaluation) {
        Evaluation existing = evaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evaluation non trouvée"));
        existing.setInscription(evaluation.getInscription());
        existing.setMatiere(evaluation.getMatiere());
        existing.setNote(evaluation.getNote());
        existing.setCommentaire(evaluation.getCommentaire());
        return evaluationRepository.save(existing);
    }
    
    public void deleteById(Long id) {
        evaluationRepository.deleteById(id);
    }
}
