package org.lpro.categorieservice.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	private String id;
	private String nom;
	private String description;

	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Sandwich> sandwiches;
    
	Category(){
    	
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
	
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Sandwich> getSandwiches() {
		return sandwiches;
	}

	public void setSandwiches(Set<Sandwich> sandwichs) {
		this.sandwiches = sandwichs;
	}
}