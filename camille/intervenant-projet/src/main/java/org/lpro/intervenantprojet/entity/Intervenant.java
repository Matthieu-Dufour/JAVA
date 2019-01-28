package org.lpro.intervenantprojet.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Intervenant {

    @Id
    private String id;
    private String nom;

    @OneToMany(mappedBy = "intervenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Projet> projets;

    Intervenant() {
        // necessaire pour JPA !
    }

    public Intervenant(String nom) {
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

    public Set<Projet> getProjets() {
        return this.projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

}