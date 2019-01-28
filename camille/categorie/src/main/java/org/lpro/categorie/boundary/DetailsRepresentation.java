package org.lpro.categorie.boundary;

import org.lpro.categorie.entity.Details;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.*;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.*;

//Annotation pour controller rest
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Details.class)
public class DetailsRepresentation {

    private final DetailsResource ir;
    private final CategorieResource cr;

    public DetailsRepresentation(DetailsResource ir, CategorieResource cr) {
        this.ir = ir;
        this.cr = cr;
    }

    @GetMapping
    /*
     * public String listeIntervenants(){ return "Toto, Duke, Anna"; }
     */
    public ResponseEntity<?> getAll() {
        Iterable<Details> allDetails = ir.findAll();
        return new ResponseEntity<>(allDetails, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMethodeAvecId(@PathVariable("id") String id) {
        String det = "{\"type\": \"ressource\", \"locale\": \"fr-FR\", \"categorie\":"
                + cr.findById(id).get().toString() + "}";
        return new ResponseEntity<>(det, HttpStatus.OK);
    }

    @PostMapping
    /*
     * public String postMethode() { return "Appel de POST"; }
     */
    public ResponseEntity<?> postMethode(@RequestBody Details details) {
        details.setId(UUID.randomUUID().toString());
        Details saved = ir.save(details);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(linkTo(DetailsRepresentation.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping
    public String putMethode() {
        return "Appel de PUT";
    }

    @DeleteMapping("/{id}")
    public void deleteMethode(@PathVariable("id") String id) {
    }

}