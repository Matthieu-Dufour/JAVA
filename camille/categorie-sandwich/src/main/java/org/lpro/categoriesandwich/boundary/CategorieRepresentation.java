package org.lpro.categoriesandwich.boundary;

import java.util.Optional;
import java.util.UUID;

import org.lpro.categoriesandwich.entity.Categorie;
import org.lpro.categoriesandwich.exception.NotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.ExposesResourceFor;

//Annotation pour controller rest
@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Categorie.class)
public class CategorieRepresentation {

    private final CategorieResource cr;

    public CategorieRepresentation(CategorieResource cr) {
        this.cr = cr;
    }

    @GetMapping
    /*
     * public String listeCategories(){ return "Toto, Duke, Anna"; }
     */
    public ResponseEntity<?> getAllCategories() {
        Iterable<Categorie> allCategories = cr.findAll();
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/{categorieId}")
    public ResponseEntity<?> getMethodeAvecId(@PathVariable("categorieId") String id) throws NotFound {
        return Optional.ofNullable(cr.findById(id)).filter(Optional::isPresent)
                .map(categorie -> new ResponseEntity<>(categorie.get(), HttpStatus.OK))
                .orElseThrow(() -> new NotFound("Categorie inexistante !"));
    }

    @PostMapping
    /*
     * public String postMethode() { return "Appel de POST"; }
     */
    public ResponseEntity<?> postMethode(@RequestBody Categorie categorie) {
        categorie.setId(UUID.randomUUID().toString());
        Categorie saved = cr.save(categorie);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(linkTo(CategorieRepresentation.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{categorieId}")
    public ResponseEntity<?> putMethode(@PathVariable("categorieId") String id, @RequestBody Categorie categorieUpdated)
            throws NotFound {
        return cr.findById(id).map(categorie -> {
            categorie.setId(categorieUpdated.getId());
            cr.save(categorie);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new NotFound("Categorie inexistante !"));
    }

    @DeleteMapping(value = "/{categorieId}")
    public ResponseEntity<?> deleteMethode(@PathVariable("categorieId") String id) throws NotFound {
        return cr.findById(id).map(categorie -> {
            cr.delete(categorie);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new NotFound("Categorie inexistante !"));
    }

}