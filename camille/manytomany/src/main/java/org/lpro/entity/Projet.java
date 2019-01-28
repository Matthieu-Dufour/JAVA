package org.lpro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;

@Entity
@Table(name = "projet")
public class Projet implements Serializable {

    private static final long serialVersionUID = -748956247024967638L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projet_id")
    private long id;
    private String lieu;

    @Column(name = "projet_date")
    @JsonFormat(pattern = "YYYY-MM-dd")
    private Date date;

    @ManyToMany
    @JsonBackReference
    @JoinTable(name = "intervenants_projets", joinColumns = @JoinColumn(name = "projet_id", referencedColumnName = "projet_id"), inverseJoinColumns = @JoinColumn(name = "intervenant_id", referencedColumnName = "intervenant_id"))
    private Set<Intervenant> intervenant = new HashSet<Intervenant>();

    Projet() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Intervenant> getIntervenant() {
        return intervenant;
    }

    public void setIntervenant(Set<Intervenant> intervenant) {
        this.intervenant = intervenant;
    }

}
