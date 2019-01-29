package org.lpro.categorieservice.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Sandwich {
	 	@Id
	    private String id;
	    private String nom;
	    
		@ManyToOne(fetch=FetchType.LAZY, optional = false)
	    @JoinColumn(name = "category_id", nullable = false)
	    @JsonIgnore
	    private Category category;
		
		Sandwich(){
			
		}	    
	    
		public Sandwich(String nom) {
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


		public Category getCategory() {
			return category;
		}


		public void setCategory(Category category) {
			this.category = category;
		}   
	    
}