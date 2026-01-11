package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité Apprenant représentant un apprenant
 */
@Entity
@Table(name = "apprenants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Apprenant {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false)
    private String telephone;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "apprenant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inscription> inscriptions = new ArrayList<>();
    
    public String getNomComplet() {
        return prenom + " " + nom;
    }
}
