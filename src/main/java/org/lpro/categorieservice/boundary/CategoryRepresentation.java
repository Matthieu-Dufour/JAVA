/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lpro.categorieservice.boundary;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.lpro.categorieservice.entity.Category;
import org.lpro.categorieservice.exception.Conflict;
import org.lpro.categorieservice.exception.NotFound;
/**
 *
 * @author dufour76u
 */
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Category.class)
public class CategoryRepresentation {
	private final CategoryResource cr;
	private final SandwichResource sr;

	public CategoryRepresentation(CategoryResource cr, SandwichResource sr) {
		this.cr = cr;
		this.sr = sr;
	}

	@GetMapping
	public ResponseEntity<?> getAllCategories() {
		Iterable<Category> allCategories = cr.findAll();
		return new ResponseEntity<>(category2Resource(allCategories), HttpStatus.OK);
	}
	
    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<?> getOne(@PathVariable("categoryId") String id)
            throws NotFound {
        return Optional.ofNullable(cr.findById(id))
                .filter(Optional::isPresent)
                .map(category -> new ResponseEntity<>(categoryToResource(category.get(),false), HttpStatus.OK))
                .orElseThrow(() -> new NotFound("Categorie inexistante"));
    }

	private Resources<Resource<Category>> category2Resource(Iterable<Category> categories) {
		Link selfLink = linkTo(CategoryRepresentation.class).withSelfRel();
		List<Resource<Category>> categoryResources = new ArrayList<>();
		categories.forEach(category
				-> categoryResources.add(categoryToResource(category, false)));
		return new Resources<>(categoryResources, selfLink);
	}

	private Resource<Category> categoryToResource(Category category, Boolean collection) {
		Link selfLink = linkTo(CategoryRepresentation.class)
				.slash(category.getId())
				.withSelfRel();
		if (collection) {
			Link collectionLink = linkTo(CategoryRepresentation.class).withRel("collection");
			return new Resource<>(category, selfLink, collectionLink);
		} else {
			return new Resource<>(category, selfLink);
		}
	}
	


	@PostMapping
	public ResponseEntity<?> postMethod(@RequestBody Category category) {
		category.setId(UUID.randomUUID().toString());
		List<String> categoryNames = new ArrayList<>();
		for(Category cat : cr.findAll()) {
			categoryNames.add(cat.getNom());
		}
		if(categoryNames.contains(category.getNom())) {
			throw new Conflict("Ce nom de catégorie existe déjà !");
		}else {
			Category saved = cr.save(category);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setLocation(linkTo(CategoryRepresentation.class).slash(saved.getId()).toUri());
			return ResponseEntity.created(responseHeaders.getLocation())
					.body(saved);
		}
	}
	

    @PutMapping(value = "/{categoryId}")
    public ResponseEntity<?> putMethod(@PathVariable("categoryId") String id,
            @RequestBody Category categoryUpdated) throws NotFound {
        return cr.findById(id)
                .map(category -> {
                	category.setId(categoryUpdated.getId());
                	category.setNom(categoryUpdated.getNom());
                	category.setDescription(categoryUpdated.getDescription());
                    Category saved = cr.save(category);
                    return ResponseEntity.ok().body(saved);
                }).orElseThrow(() -> new NotFound("Catégorie inexistante"));
    }
	
	

}