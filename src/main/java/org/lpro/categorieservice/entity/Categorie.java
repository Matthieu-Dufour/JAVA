package org.lpro.categorieservice.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categorie {

    @Id
    private String id;
    private String nom;
    
    @OneToMany(mappedBy="categorie", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Sandwich> sandwichs;
    
    Categorie() {
        // necessaire pour JPA !!!!
    }
    
    public Categorie (String nom) {
        this.nom = nom;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Sandwich> getSandwichs() {
        return sandwichs;
    }

    public void setSandwichs(Set<Sandwich> sandwichs) {
        this.sandwichs = sandwichs;
    }
    
    
    
    
    
}
