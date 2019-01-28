package org.lpro.intervenantprojet.boundary;

import java.util.Optional;
import java.util.UUID;

import org.lpro.intervenantprojet.entity.Intervenant;
import org.lpro.intervenantprojet.exception.NotFound;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.ExposesResourceFor;

//Annotation pour controller rest
@RestController
@RequestMapping(value = "/intervenants", produces = MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Intervenant.class)
public class IntervenantRepresentation {

    private final IntervenantResource ir;
    private final ProjetResource pr;

    public IntervenantRepresentation(IntervenantResource ir, ProjetResource pr) {
        this.ir = ir;
        this.pr = pr;
    }

    @GetMapping
    public ResponseEntity<?> getAllIntervenants(@RequestParam(value = "page", required = true) Integer page,
            @RequestParam(value = "limit", required = true) Integer limit) {
        return new ResponseEntity<>(ir.findAll(PageRequest.of(page, limit)), HttpStatus.OK);
    }

    /*
     * @GetMapping public ResponseEntity<?> getAllIntervenants(@RequestParam(value =
     * "nom", required = false) Optional<String> nom) { if (nom.isPresent()) {
     * Iterable<Intervenant> allIntervenants = ir.findByNomIgnoreCase(nom.get());
     * return new ResponseEntity<>(allIntervenants, HttpStatus.OK); } else {
     * Iterable<Intervenant> allIntervenants = ir.findAll(); return new
     * ResponseEntity<>(allIntervenants, HttpStatus.OK); } }
     */

    /*
     * @GetMapping public ResponseEntity<?> getAllIntervenants() {
     * Iterable<Intervenant> allIntervenants = ir.findAll(); return new
     * ResponseEntity<>(allIntervenants, HttpStatus.OK); }
     */

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