package org.lpro.categorieservice.boundary;

import java.util.UUID;
import org.lpro.categorieservice.entity.Sandwich;
import org.lpro.categorieservice.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SandwichRepresentation {
    
    private final SandwichResource pr;
    private final CategorieResource ir;

    // Injection de dépendances
    public SandwichRepresentation(SandwichResource pr, CategorieResource ir) {
        this.pr = pr;
        this.ir = ir;
    }
    
    @GetMapping("/categories/{id}/sandwichs")
    public ResponseEntity<?> getProjetByIntervenantId(@PathVariable("id") String id)
            throws NotFound {
        
        if (!ir.existsById(id)) {
            throw new NotFound("Categorie inexistant");
        }
        return new ResponseEntity<>(pr.findByCategorieId(id), HttpStatus.OK);
    }
  
    @PostMapping("/categories/{id}/sandwichs")
    public ResponseEntity<?> ajoutSandwich(@PathVariable("id") String id,
            @RequestBody Sandwich sandwich) throws NotFound {
        return ir.findById(id)
                .map(categorie -> {
                    sandwich.setId(UUID.randomUUID().toString());
                    sandwich.setCategorie(categorie);
                    pr.save(sandwich);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                }).orElseThrow ( () -> new NotFound("Categorie inexistant"));
    }
    
    @PutMapping("/categories/{categorieId}/sandwichs/{sandwichId}")
    public ResponseEntity<?> updateProjet(@PathVariable("categorieId") String categorieId,
            @PathVariable("sandwichId") String sandwichId,
            @RequestBody Sandwich sandwichUpdated) {
        
        if (!ir.existsById(categorieId)) {
            throw new NotFound("Categorie inexistant");
        }
        return pr.findById(sandwichId)
                .map(sandwich -> {
                    sandwichUpdated.setId(sandwich.getId());
                    pr.save(sandwichUpdated);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }).orElseThrow(() -> new NotFound("Sandwich inexistant"));
    }
     
    @DeleteMapping("/categories/{categorieId}/sandwichs/{sandwichId}")
    public ResponseEntity<?> deleteSandiwch(@PathVariable("categorieId") String categorieId,
            @PathVariable("sandwichId") String sandwichId) {
        
        if (!ir.existsById(categorieId)) {
            throw new NotFound("Categorie inexistant");
        }
        return pr.findById(sandwichId)
                .map(sandwich -> {
                    pr.delete(sandwich);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }).orElseThrow(() -> new NotFound("Sandwich inexistant"));
    }
    
}
