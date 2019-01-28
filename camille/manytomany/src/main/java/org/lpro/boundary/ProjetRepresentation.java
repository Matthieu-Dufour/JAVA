package org.lpro.boundary;

import java.util.Collection;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.lpro.entity.Projet;
import org.lpro.entity.Intervenant;

@RestController
@RequestMapping("/projets")
public class ProjetRepresentation {

    private final ProjetRepository projetsRepo;

    public ProjetRepresentation(ProjetRepository pr) {
        this.projetsRepo = pr;
    }

    @GetMapping()
    public ResponseEntity<Collection<Projet>> getProjets() {
        return new ResponseEntity<>(projetsRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Projet> getProjet(@PathVariable long id) {
        Optional<Projet> projet = projetsRepo.findById(id);

        if (projet.isPresent()) {
            return new ResponseEntity<>(projet.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addProjet(@RequestBody Projet projet) {
        return new ResponseEntity<>(projetsRepo.save(projet), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable long id) {
        Optional<Projet> projet = projetsRepo.findById(id);

        if (projet.isPresent()) {
            projetsRepo.delete(projet.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/intervenants")
    public ResponseEntity<Collection<Intervenant>> getProjetsIntervenants(@PathVariable("id") long id) {
        Optional<Projet> projet = projetsRepo.findById(id);

        if (projet.isPresent()) {
            return new ResponseEntity<>(projet.get().getIntervenant(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
