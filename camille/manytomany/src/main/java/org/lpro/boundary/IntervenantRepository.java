package org.lpro.boundary;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.lpro.entity.Intervenant;

public interface IntervenantRepository extends CrudRepository<Intervenant, Long> {

    Collection<Intervenant> findAll();

}
