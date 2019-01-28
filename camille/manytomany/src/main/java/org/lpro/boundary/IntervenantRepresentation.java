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
@RequestMapping("/intervenants")
public class IntervenantRepresentation {

    private final IntervenantRepository intervenantRepo;

    public IntervenantRepresentation(IntervenantRepository ir) {
        this.intervenantRepo = ir;
    }

    @GetMapping()
    public ResponseEntity<Collection<Intervenant>> getIntervenants() {
         return new ResponseEntity<>(intervenantRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Intervenant> getIntervenant(@PathVariable long id) {
        Optional<Intervenant> intervenant = intervenantRepo.findById(id);

        if (intervenant.isPresent()) {
            return new ResponseEntity<>(intervenant.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<?> addIntervenant(@RequestBody Intervenant intervenant) {
        return new ResponseEntity<>(intervenantRepo.save(intervenant), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteIntervenant(@PathVariable long id) {
        Optional<Intervenant> intervenant = intervenantRepo.findById(id);
        if (intervenant.isPresent()) {
            intervenantRepo.delete(intervenant.get());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/projets")
    public ResponseEntity<Collection<Projet>> getIntervenantsProjets(@PathVariable long id) {
        Optional<Intervenant> intervenant = intervenantRepo.findById(id);

        if (intervenant.isPresent()) {
            return new ResponseEntity<>(intervenant.get().getProjets(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
