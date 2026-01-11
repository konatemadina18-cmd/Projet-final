package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entité Formateur représentant un formateur
 */
@Entity
@Table(name = "formateurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Formateur {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String specialite;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "centre_id", nullable = false)
    private Centre centre;
    
    @Column
    private String email;
    
    @Column
    private String telephone;
}
