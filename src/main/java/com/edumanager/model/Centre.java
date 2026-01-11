package com.edumanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité Centre représentant un centre de formation
 */
@Entity
@Table(name = "centres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Centre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String localisation;
    
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Programme> programmes = new ArrayList<>();
    
    @OneToMany(mappedBy = "centre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Formateur> formateurs = new ArrayList<>();
}
