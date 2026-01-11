package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entité Paiement représentant un paiement effectué par un apprenant
 */
@Entity
@Table(name = "paiements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inscription_id", nullable = false)
    private Inscription inscription;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;
    
    @Column(name = "date_paiement", nullable = false)
    private LocalDate datePaiement;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModePaiement mode;
    
    public enum ModePaiement {
        ESPECE, CHEQUE, VIREMENT, CARTE_BANCAIRE, MOBILE_MONEY
    }
}
