/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lpro.categorieservice.boundary;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.UUID;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.lpro.categorieservice.entity.Sandwich;
import org.lpro.categorieservice.exception.NotFound;

/**
 *
 * @author dufour76u
 */
@RestController
public class SandwichRepresentation {
	private final SandwichResource sr;
	private final CategoryResource cr;

	public SandwichRepresentation(SandwichResource sr, CategoryResource cr) {
		this.sr = sr;
		this.cr = cr;
	}
	
	  @GetMapping("/categories/{id}/sandwiches")
	    public ResponseEntity<?> getSandwichByCategoryId(@PathVariable("id") String id)
	            throws NotFound {
	        
	        if (!cr.existsById(id)) {
	            throw new NotFound("Categorie inexistante");
	        }
	        return new ResponseEntity<>(sr.findByCategoryId(id), HttpStatus.OK);
	    }

	
    @PostMapping("/categories/{id}/sandwiches")
    public ResponseEntity<?> ajoutSandwich(@PathVariable("id") String id,
            @RequestBody Sandwich sandwich) throws NotFound {
        return cr.findById(id)
                .map(category -> {
                    sandwich.setId(UUID.randomUUID().toString());
                    sandwich.setCategory(category);
                    Sandwich saved = sr.save(sandwich);
                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.setLocation(linkTo(SandwichRepresentation.class).slash(saved.getId()).toUri());
                    return new ResponseEntity<>(null,responseHeaders,HttpStatus.CREATED);
                }).orElseThrow ( () -> new NotFound("Categorie inexistante"));
    }

}
