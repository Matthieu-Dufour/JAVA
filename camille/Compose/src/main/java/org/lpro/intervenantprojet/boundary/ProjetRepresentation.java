package org.lpro.intervenantprojet.boundary;

import java.util.UUID;

import org.lpro.intervenantprojet.entity.Projet;
import org.lpro.intervenantprojet.exception.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

//Annotation pour controller rest
@RestController
public class ProjetRepresentation {

    private final ProjetResource pr;
    private final IntervenantResource ir;

    // Injection de dépendances
    public ProjetRepresentation(ProjetResource pr, IntervenantResource ir) {
        this.pr = pr;
        this.ir = ir;
    }

    @GetMapping("/intervenants/{intervenantId}/projets")
    /*
     * public String listeIntervenants(){ return "Toto, Duke, Anna"; }
     */
    public ResponseEntity<?> getAllProjetsByIntervenantId(@PathVariable("intervenantId") String id) throws NotFound {
        if (!ir.existsById(id)) {
            throw new NotFound("Intervenant inexistant !");
        }
        return new ResponseEntity<>(pr.findByIntervenantId(id), HttpStatus.OK);
    }

    @PostMapping("/intervenants/{intervenantId}/projets")
    public ResponseEntity<?> addProjet(@PathVariable("intervenantId") String id, @RequestBody Projet projet)
            throws NotFound {
        return ir.findById(id).map(intervenant -> {
            projet.setId(UUID.randomUUID().toString());
            projet.setIntervenant(intervenant);
            pr.save(projet);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }).orElseThrow(() -> new NotFound("Intervenant inexistant !"));
    }

    @PutMapping("/intervenants/{intervenantId}/projets/{projetId}")
    public ResponseEntity<?> putProjet(@PathVariable("intervenantId") String idIntervenant,
            @PathVariable("projetId") String idProjet, @RequestBody Projet projetUpdated) throws NotFound {
        // vérifier si intervenant existe
        // modifier l'id du projet pour le projet en paramètre
        // sauvegarder le projet
        // traiter le cas où le projet n'existe pas
        /*
         * if (!ir.existsById(idIntervenant)) { throw new
         * NotFound("Intervenant inexistant !"); } return
         * pr.findById(idProjet).map(projet -> { projet.setId(projetUpdated.getId());
         * pr.save(projet); return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         * }).orElseThrow(() -> new NotFound("Projet inexistant !"));
         */
        return ir.findById(idIntervenant).map(intervenant -> {
            if (!pr.existsById(idProjet)) {
                throw new NotFound("Projet inexistant !");
            }
            projetUpdated.setIntervenant(intervenant);
            pr.save(projetUpdated);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }).orElseThrow(() -> new NotFound("Intervenant inexistant !"));
    }

    @DeleteMapping("/intervenants/{intervenantId}/projets/{projetId}")
    public ResponseEntity<?> deleteProjet(@PathVariable("intervenantId") String idIntervenant,
            @PathVariable("projetId") String idProjet) throws NotFound {
        if (!ir.existsById(idIntervenant)) {
            throw new NotFound("Intervenant inexistant !");
        }
        return pr.findById(idProjet).map(projet -> {
            pr.delete(projet);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new NotFound("Projet inexistant !"));
    }

}