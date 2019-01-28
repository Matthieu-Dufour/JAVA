package org.lpro.boundary;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.lpro.entity.Projet;

public interface ProjetRepository extends CrudRepository<Projet, Long> {

    Collection<Projet> findAll();

}
