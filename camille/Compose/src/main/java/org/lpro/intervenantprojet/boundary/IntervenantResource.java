package org.lpro.intervenantprojet.boundary;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.lpro.intervenantprojet.entity.Intervenant;
import org.springframework.data.repository.CrudRepository;

public interface IntervenantResource extends CrudRepository<Intervenant, String> {

    List<Intervenant> findByNomIgnoreCase(String nom);

    // select i from Intervenant i where i.age <= ?
    // List<Intervenant> findByAgeLessThanEqual(long age);

    List<Intervenant> findAll(Pageable page);

}