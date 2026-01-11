package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entité Evaluation représentant une évaluation d'un apprenant
 */
@Entity
@Table(name = "evaluations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_id", nullable = false)
    private Inscription inscription;
    
    @Column(nullable = false)
    private String matiere;
    
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal note; // Note sur 20
    
    @Column(columnDefinition = "TEXT")
    private String commentaire;
}
