package org.lpro.categoriesandwich.boundary;

import java.util.UUID;

import org.lpro.categoriesandwich.entity.Sandwich;
import org.lpro.categoriesandwich.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

//Annotation pour controller rest
@RestController
public class SandwichRepresentation {

    private final SandwichResource sr;
    private final CategorieResource cr;

    // Injection de dépendances
    public SandwichRepresentation(SandwichResource sr, CategorieResource cr) {
        this.sr = sr;
        this.cr = cr;
    }

    @GetMapping("/categories/{categorieId}/sandwichs")
    /*
     * public String listeCategories(){ return "Toto, Duke, Anna"; }
     */
    public ResponseEntity<?> getAllSandwichsByCategorieId(@PathVariable("categorieId") String id) throws NotFound {
        if (!cr.existsById(id)) {
            throw new NotFound("Categorie inexistante !");
        }
        return new ResponseEntity<>(sr.findByCategorieId(id), HttpStatus.OK);
    }

    @PostMapping("/categories/{categorieId}/sandwichs")
    public ResponseEntity<?> addSandwich(@PathVariable("categorieId") String id, @RequestBody Sandwich sandwich)
            throws NotFound {
        return cr.findById(id).map(categorie -> {
            sandwich.setId(UUID.randomUUID().toString());
            sandwich.setCategorie(categorie);
            sr.save(sandwich);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }).orElseThrow(() -> new NotFound("Categorie inexistante !"));
    }

    @PutMapping("/categories/{categorieId}/sandwichs/{sandwichId}")
    public ResponseEntity<?> putSandwich(@PathVariable("categorieId") String idCategorie,
            @PathVariable("sandwichId") String idSandwich, @RequestBody Sandwich sandwichUpdated) throws NotFound {
        return cr.findById(idCategorie).map(categorie -> {
            if (!sr.existsById(idSandwich)) {
                throw new NotFound("Sandwich inexistant !");
            }
            sandwichUpdated.setCategorie(categorie);
            sr.save(sandwichUpdated);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }).orElseThrow(() -> new NotFound("Categorie inexistante !"));
    }

    @DeleteMapping("/categories/{categorieId}/sandwichs/{sandwichId}")
    public ResponseEntity<?> deleteSandwich(@PathVariable("categorieId") String idCategorie,
            @PathVariable("sandwichId") String idSandwich) throws NotFound {
        if (!cr.existsById(idCategorie)) {
            throw new NotFound("Categorie inexistante !");
        }
        return sr.findById(idSandwich).map(sandwich -> {
            sr.delete(sandwich);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new NotFound("Sandwich inexistant !"));
    }

}