package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité Inscription représentant l'inscription d'un apprenant à une cohorte
 */
@Entity
@Table(name = "inscriptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscription {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apprenant_id", nullable = false)
    private Apprenant apprenant;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cohorte_id", nullable = false)
    private Cohorte cohorte;
    
    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutInscription statut;
    
    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presence> presences = new ArrayList<>();
    
    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements = new ArrayList<>();
    
    @OneToMany(mappedBy = "inscription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations = new ArrayList<>();
    
    public enum StatutInscription {
        EN_ATTENTE, CONFIRMEE, ANNULEE, TERMINEE
    }
}
