package org.lpro.categorie.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Categorie {

    @Id
    private String id;
    private String nom;
    private String desc;

    Categorie() {
        // necessaire pour JPA !
    }

    public Categorie(String id, String nom, String desc) {
        this.id = id;
        this.nom = nom;
        this.desc = desc;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public String toString() {
    	String res="{ \"id\":"+getId()+",\"nom\":\""+getNom()+"\",\"desc\":\""+getDesc()+"\"}";
		return res;
    }

}