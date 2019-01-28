package org.lpro.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;

@Entity
@Table(name = "intervenant")
public class Intervenant implements Serializable {
    private static final long serialVersionUID = -748956437024967638L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "intervenant_id")
    private long id;
    private String nom;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "intervenants_projets", joinColumns = @JoinColumn(name = "intervenant_id", referencedColumnName = "intervenant_id"), inverseJoinColumns = @JoinColumn(name = "projet_id", referencedColumnName = "projet_id"))
    private Set<Projet> projets = new HashSet<Projet>();

    Intervenant() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

}
