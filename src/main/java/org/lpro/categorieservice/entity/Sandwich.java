package org.lpro.categorieservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Sandwich {
    
    @Id
    private String id;
    private String nom;
    private int descr;
    
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "categorie_id", nullable = false)
    @JsonIgnore
    private Categorie categorie;
    
    Sandwich() {
        // pour JPA
    }

    public Sandwich(String nom, int descr) {
        this.nom = nom;
        this.descr = descr;
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

    public int getDescr() {
        return descr;
    }

    public void setDescr(int descr) {
        this.descr = descr;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
    
    
    
    
}
