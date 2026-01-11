package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entité Presence représentant la présence d'un apprenant
 */
@Entity
@Table(name = "presences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_id", nullable = false)
    private Inscription inscription;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutPresence statut;
    
    public enum StatutPresence {
        PRESENT, ABSENT, RETARD, EXCUSE
    }
}
