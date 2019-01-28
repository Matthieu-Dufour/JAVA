package org.lpro.intervenantservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Intervenant {

    @Id
    private String id;
    private String nom;

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

}