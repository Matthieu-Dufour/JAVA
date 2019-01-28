package org.lpro.intervenantservice.boundary;

import org.lpro.intervenantservice.entity.Intervenant;
import org.lpro.intervenantservice.exception.NotFound;
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
@RequestMapping(value = "/intervenants", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Intervenant.class)
public class IntervenantRepresentation {

    private final IntervenantResource ir;

    public IntervenantRepresentation(IntervenantResource ir) {
        this.ir = ir;
    }

    @GetMapping
    /*
     * public String listeIntervenants(){ return "Toto, Duke, Anna"; }
     */
    public ResponseEntity<?> getAllIntervenants() {
        Iterable<Intervenant> allIntervenants = ir.findAll();
        return new ResponseEntity<>(allIntervenants, HttpStatus.OK);
    }

    @GetMapping(value = "/{intervenantId}")
    public ResponseEntity<?> getMethodeAvecId(@PathVariable("intervenantId") String id) throws NotFound {
        return Optional.ofNullable(ir.findById(id)).filter(Optional::isPresent)
                .map(intervenant -> new ResponseEntity<>(intervenant.get(), HttpStatus.OK))
                .orElseThrow(() -> new NotFound("Intervenant inexistant !"));
    }

    @PostMapping
    /*
     * public String postMethode() { return "Appel de POST"; }
     */
    public ResponseEntity<?> postMethode(@RequestBody Intervenant intervenant) {
        intervenant.setId(UUID.randomUUID().toString());
        Intervenant saved = ir.save(intervenant);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(linkTo(IntervenantRepresentation.class).slash(saved.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{intervenantId}")
    public ResponseEntity<?> putMethode(@PathVariable("intervenantId") String id,
            @RequestBody Intervenant intervenantUpdated) throws NotFound {
        return ir.findById(id).map(intervenant -> {
            intervenant.setId(intervenantUpdated.getId());
            ir.save(intervenant);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new NotFound("Intervenant inexistant !"));
    }

    @DeleteMapping(value = "/{intervenantId}")
    public ResponseEntity<?> deleteMethode(@PathVariable("intervenantId") String id) throws NotFound {
        return ir.findById(id).map(intervenant -> {
            ir.delete(intervenant);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new NotFound("Intervenant inexistant !"));
    }

}